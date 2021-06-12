package gym.plans.domain

import HasAnId

@JvmInline
value class PlanId(private val id: String) {
    override fun toString(): String = id
}

class Plan private constructor(
    val id: PlanId,
    internal var price: Price,
    private val duration: Duration,
) : HasAnId {
    companion object {
        fun new(
            id: String,
            priceAmount: Int,
            durationInMonths: Int
        ): Plan {
            return Plan(
                PlanId(id),
                Price(priceAmount),
                Duration(durationInMonths)
            )
        }
    }

    override fun getId(): String {
        return id.toString()
    }

    fun changePrice(newPriceAmount: Int) {
        price = Price(newPriceAmount)
    }
}

internal data class Price(private val amount: Int) {
    init {
        require(amount >= 0) {
            "Price amount must be non-negative, was [$amount]"
        }
    }
}

internal data class Duration(private val durationInMonths: Int) {
    init {
        require(listOf(1, 12).contains(durationInMonths)) {
            "Plan duration is either 1 month or 12 months, was [$durationInMonths]"
        }
    }
}
