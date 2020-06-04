package gym.subscriptions.domain

import common.Aggregate
import common.AggregateId
import java.time.LocalDate

inline class SubscriptionId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Subscription private constructor(
    override val id: SubscriptionId,
    private val durationInMonths: Int,
    internal val startDate: LocalDate,
    internal var endDate: LocalDate,
    internal val price: Price
) : Aggregate {
    companion object {
        fun subscribe(
            subscriptionId: String,
            planDurationInMonths: Int,
            startDate: LocalDate,
            planPrice: Int,
            email: String,
            isStudent: Boolean
        ): Subscription {
            val priceAfterDiscount = Price(planPrice).applyDiscount(Discount(planDurationInMonths, isStudent))
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
        endDate = endDate.plusMonths(durationInMonths.toLong())
    }

    fun willBeEndedAfter(asFrom: LocalDate): Boolean {
        return asFrom.isAfter(endDate)
    }

    fun isOngoing(asOfDate: LocalDate): Boolean {
        return asOfDate in startDate..endDate
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

    internal fun applyDiscount(discount: Discount): Price {
        return Price((amount.toDouble() * (1 - discount.rate)).toInt())
    }
}

internal data class Discount(internal var rate: Double = 0.0) {
    constructor(durationInMonths: Int, isStudent: Boolean) : this() {
        if (durationInMonths == 12) {
            rate += 0.3
        }
        if (isStudent) {
            rate += 0.2
        }
    }
}
