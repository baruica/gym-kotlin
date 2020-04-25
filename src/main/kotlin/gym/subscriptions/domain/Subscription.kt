package gym.subscriptions.domain

import gym.subscriptions.domain.SubscriptionEvent.NewSubscription
import gym.subscriptions.domain.SubscriptionEvent.SubscriptionRenewed
import java.time.LocalDate

inline class SubscriptionId(private val id: String) {
    override fun toString(): String = id
}

class Subscription(
    val id: SubscriptionId,
    startDate: LocalDate,
    planPrice: Int,
    planDurationInMonths: Int,
    isStudent: Boolean,
    email: String
) {
    val price: Int = Price(planPrice).afterDiscount(planDurationInMonths, isStudent)

    private val periods: MutableList<Period> = mutableListOf(
        Period(startDate, planDurationInMonths)
    )

    val raisedEvents: MutableList<SubscriptionEvent> = mutableListOf()

    init {
        raisedEvents.add(
            NewSubscription(id.toString(), startDate.toString(), email)
        )
    }

    fun renew() {
        val oldEndOfSubscription: LocalDate = periods.last().endDate

        periods.add(
            periods.last().next()
        )

        raisedEvents.add(
            SubscriptionRenewed(
                id.toString(),
                oldEndOfSubscription.toString(),
                periods.last().endDate.toString()
            )
        )
    }

    fun isOngoing(asOfDate: LocalDate): Boolean {
        return periods.last().contains(asOfDate)
    }

    fun willBeEnded(asFrom: LocalDate): Boolean {
        return periods.last().isBefore(asFrom)
    }

    fun monthlyTurnover(): Double {
        return (price / periods.first().durationInMonths).toDouble()
    }
}

private data class Price(val amount: Int) {
    internal fun afterDiscount(durationInMonths: Int, isStudent: Boolean): Int {
        return (amount.toDouble() * (1 - Discount(durationInMonths, isStudent).rate)).toInt()
    }
}

private class Discount(durationInMonths: Int, isStudent: Boolean) {
    internal var rate: Double = 0.0

    init {
        if (durationInMonths == 12) {
            rate += 0.3
        }
        if (isStudent) {
            rate += 0.2
        }
    }
}

private data class Period(private val startDate: LocalDate, val durationInMonths: Int) {

    internal val endDate: LocalDate = (startDate.plusMonths(durationInMonths.toLong())).minusDays(1)

    internal fun isBefore(date: LocalDate): Boolean = date.isAfter(endDate)

    internal fun contains(asOfDate: LocalDate): Boolean {
        return (asOfDate.isAfter(startDate) || asOfDate == startDate)
            && (asOfDate.isBefore(endDate) || asOfDate == endDate)
    }

    internal fun next(): Period {
        val firstDayOfNextPeriod = endDate.plusDays(1)
        val nbMonthsInCurrentPeriod = java.time.Period.between(startDate, firstDayOfNextPeriod).months

        return Period(firstDayOfNextPeriod, nbMonthsInCurrentPeriod)
    }
}
