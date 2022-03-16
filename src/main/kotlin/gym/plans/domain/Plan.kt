package gym.plans.domain

import Aggregate

@JvmInline
value class PlanId(private val value: String) {
    override fun toString(): String = value
}

class Plan private constructor(
    val id: PlanId,
    internal var price: Price,
    private val duration: Duration,
) : Aggregate {

    override fun getId(): String = id.toString()

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

@JvmInline
internal value class Price(private val value: Int) {
    init {
        require(value >= 0) {
            "Price amount must be non-negative, was [$value]"
        }
    }
}

@JvmInline
internal value class Duration(private val value: Int) {
    init {
        require(listOf(1, 12).contains(value)) {
            "Plan duration is either 1 month or 12 months, was [$value]"
        }
    }
}
