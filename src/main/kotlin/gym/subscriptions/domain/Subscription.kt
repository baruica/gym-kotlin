package gym.subscriptions.domain

import common.Aggregate
import common.AggregateId
import java.time.LocalDate

inline class SubscriptionId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Subscription(
    override val id: SubscriptionId,
    private val startDate: LocalDate,
    private val planDurationInMonths: Int,
    planPrice: Int,
    email: String,
    isStudent: Boolean
) : Aggregate {
    override val raisedEvents = mutableListOf<SubscriptionEvent>()

    val price = Price(planPrice).afterDiscount(planDurationInMonths, isStudent)
    private var endDate: LocalDate = startDate.plusMonths(planDurationInMonths.toLong()).minusDays(1)

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
    init {
        require(amount >= 0) {
            "Price amount must be non-negative, was $amount"
        }
    }

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
