package gym.subscriptions.domain

import java.time.LocalDate

inline class SubscriptionId(private val id: String) {
    override fun toString(): String {
        return id
    }
}

class Subscription(
    val subscriptionId: SubscriptionId,
    startDate: LocalDate,
    planId: String,
    planPrice: Int,
    planDurationInMonths: Int,
    isStudent: Boolean,
    email: String
) {
    private val chosenPlan: ChosenPlan = ChosenPlan(planId, planPrice, planDurationInMonths)
    internal val price: Int
    private val periods: MutableList<Period>

    val raisedEvents: MutableList<SubscriptionEvent> = mutableListOf()

    init {
        this.price = Price(chosenPlan.price).afterDiscount(
            chosenPlan.isYearly(),
            isStudent
        )

        this.periods = mutableListOf(
            Period(startDate, chosenPlan.durationInMonths)
        )

        raisedEvents.add(
            SubscriptionEvent.NewSubscription(
                subscriptionId.toString(),
                startDate.toString(),
                email
            )
        )
    }

    fun renew() {
        periods.add(
            periods.last().next()
        )

        raisedEvents.add(
            SubscriptionEvent.SubscriptionRenewed(subscriptionId.toString())
        )
    }

    fun isOngoing(asOfDate: LocalDate): Boolean {
        return periods.last().contains(asOfDate)
    }

    fun willBeEnded(asFrom: LocalDate): Boolean {
        return periods.last().isBefore(asFrom)
    }

    fun monthlyTurnover(): Double {
        return when (chosenPlan.isYearly()) {
            true -> (price / 12).toDouble()
            false -> price.toDouble()
        }
    }
}

private data class ChosenPlan(val planId: String, val price: Int, val durationInMonths: Int) {
    internal fun isYearly(): Boolean {
        return durationInMonths == 12
    }
}

private data class Price(val price: Int) {
    internal fun afterDiscount(chosenPlanIsYearly: Boolean, isStudent: Boolean): Int {
        return (price.toDouble() * (1 - Discount(chosenPlanIsYearly, isStudent).rate)).toInt()
    }
}

private class Discount(chosenPlanIsYearly: Boolean, isStudent: Boolean) {
    internal var rate: Double = 0.0

    init {
        if (chosenPlanIsYearly) {
            rate += 0.3
        }
        if (isStudent) {
            rate += 0.2
        }
    }
}

internal class Period(private val startDate: LocalDate, durationInMonths: Int) {

    private val endDate: LocalDate = (startDate.plusMonths(durationInMonths.toLong())).minusDays(1)

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
