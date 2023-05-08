package gym.plans.domain

import Aggregate
import Id

class Plan private constructor(
    val id: Id<String>,
    internal var price: Price,
    private val duration: Duration,
) : Aggregate {

    override fun getId(): String = id.toString()

    companion object {
        fun new(
            id: Id<String>,
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

    fun changePrice(newPriceAmount: Int): Plan {
        return Plan(
            id,
            Price(newPriceAmount),
            duration
        )
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
