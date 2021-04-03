package gym.subscriptions.domain

import common.Aggregate
import common.AggregateId
import java.time.LocalDate
import java.time.Period

inline class SubscriptionId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Subscription private constructor(
    override val id: SubscriptionId,
    private val durationInMonths: Int,
    internal val startDate: LocalDate,
    internal var endDate: LocalDate,
    internal val price: Price,
) : Aggregate {
    companion object {
        fun subscribe(
            subscriptionId: String,
            planDurationInMonths: Int,
            startDate: LocalDate,
            planPrice: Int,
            isStudent: Boolean
        ): Subscription {
            val priceAfterDiscount = Price(planPrice).applyDiscount(planDurationInMonths, isStudent)
            val endDate = startDate.plusMonths(planDurationInMonths.toLong()).minusDays(1)

            return Subscription(
                SubscriptionId(subscriptionId),
                planDurationInMonths,
                startDate,
                endDate,
                priceAfterDiscount
            )
        }
    }

    fun renew() {
        endDate = endDate.plus(Period.ofMonths(durationInMonths))
    }

    fun willBeEndedAfter(asFrom: LocalDate): Boolean {
        return asFrom.isAfter(endDate)
    }

    fun isOngoing(asOfDate: LocalDate): Boolean {
        return asOfDate in startDate..endDate
    }

    fun isMonthly(): Boolean {
        return durationInMonths == 1
    }

    fun monthlyTurnover(): Int {
        return (price.amount / durationInMonths)
    }
}

internal data class Price(val amount: Int) {
    init {
        require(amount >= 0) {
            "Price amount must be non-negative, was [$amount]"
        }
    }

    internal fun applyDiscount(durationInMonths: Int, isStudent: Boolean): Price {
        var rate = 0.0
        if (durationInMonths == 12) {
            rate += 0.1
        }
        if (isStudent) {
            rate += 0.2
        }
        return Price((amount.toDouble() * (1 - rate)).toInt())
    }
}
