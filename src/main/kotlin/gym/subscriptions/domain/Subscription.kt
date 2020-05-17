package gym.subscriptions.domain

import gym.subscriptions.domain.SubscriptionEvent.NewSubscription
import gym.subscriptions.domain.SubscriptionEvent.SubscriptionRenewed
import java.time.LocalDate

inline class SubscriptionId(private val id: String) {
    override fun toString(): String = id
}

class Subscription(
    val id: SubscriptionId,
    private val startDate: LocalDate,
    planPrice: Int,
    private val planDurationInMonths: Int,
    email: String,
    isStudent: Boolean
) {
    val price = Price(planPrice).afterDiscount(planDurationInMonths, isStudent)
    private var endDate: LocalDate = startDate.plusMonths(planDurationInMonths.toLong()).minusDays(1)

    val raisedEvents: MutableList<SubscriptionEvent> = mutableListOf()

    init {
        raisedEvents.add(
            NewSubscription(id.toString(), startDate.toString(), email)
        )
    }

    fun renew() {
        val oldEndDate = endDate

        endDate = oldEndDate.plusMonths(planDurationInMonths.toLong())

        raisedEvents.add(
            SubscriptionRenewed(
                id.toString(),
                oldEndDate.toString(),
                endDate.toString()
            )
        )
    }

    fun willBeEndedAfter(asFrom: LocalDate): Boolean {
        return asFrom.isAfter(endDate)
    }

    fun isOngoing(asOfDate: LocalDate): Boolean {
        return asOfDate in startDate..endDate
    }

    fun monthlyTurnover(): Double {
        return (price / planDurationInMonths).toDouble()
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