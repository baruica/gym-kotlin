package gym.subscriptions.domain

import gym.plans.domain.PlanId
import java.time.LocalDate

inline class SubscriptionId(val id: String)

class Subscription(
    val id: SubscriptionId,
    val startDate: LocalDate,
    planId: PlanId,
    planPrice: Int,
    planDurationInMonths: Int,
    isStudent: Boolean
) {
    private val chosenPlan = ChosenPlan(
        planId,
        planPrice,
        planDurationInMonths
    )

    internal val price: Int =
        Price(chosenPlan.price).afterDiscount(
            chosenPlan.isYearly(),
            isStudent
        )

    private val periods: MutableList<Period> =
        mutableListOf(
            Period(startDate, chosenPlan.durationInMonths)
        )

    fun renew() {
        periods.add(
            periods.last().next()
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

data class ChosenPlan(
    val planId: PlanId,
    val price: Int,
    val durationInMonths: Int
) {
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
