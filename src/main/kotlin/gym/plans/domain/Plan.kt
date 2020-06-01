package gym.plans.domain

import common.Aggregate
import common.AggregateId

inline class PlanId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Plan private constructor(
    override val id: PlanId,
    internal var price: Price,
    private val duration: Duration
) : Aggregate {
    companion object {
        fun new(
            id: PlanId,
            priceAmount: Int,
            durationInMonths: Int
        ): Plan {
            return Plan(
                id,
                Price(priceAmount),
                Duration(durationInMonths)
            )
        }
    }

    fun changePrice(newPriceAmount: Int) {
        price = Price(newPriceAmount)
    }
}

internal data class Price(val amount: Int) {
    init {
        require(amount >= 0) {
            "Price amount must be non-negative, was $amount"
        }
    }
}

internal data class Duration(val durationInMonths: Int) {
    init {
        require(listOf(1, 12).contains(durationInMonths)) {
            "Plan duration is either 1 month or 12 months, was $durationInMonths"
        }
    }
}
