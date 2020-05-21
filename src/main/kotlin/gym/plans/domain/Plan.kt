package gym.plans.domain

import common.Aggregate

inline class PlanId(private val id: String) {
    override fun toString(): String = id
}

class Plan(val id: PlanId, priceAmount: Int, durationInMonths: Int) : Aggregate {

    override val raisedEvents = mutableListOf<PlanEvent>()

    internal var price = Price(priceAmount)
    private val duration = Duration(durationInMonths)

    init {
        raisedEvents.add(
            NewPlanCreated(
                id.toString(),
                price.amount,
                duration.durationInMonths
            )
        )
    }

    fun changePrice(newPriceAmount: Int) {
        val oldPrice = price.amount

        price = Price(newPriceAmount)

        raisedEvents.add(
            PlanPriceChanged(id.toString(), oldPrice, price.amount)
        )
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
