package gym.plans.domain

import gym.plans.domain.PlanEvent.NewPlanCreated
import gym.plans.domain.PlanEvent.PlanPriceChanged

inline class PlanId(private val id: String) {
    override fun toString(): String = id
}

class Plan(val id: PlanId, priceAmount: Int, durationInMonths: Int) {

    val raisedEvents: MutableList<PlanEvent> = mutableListOf()

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
        val oldPrice = this.price.amount

        this.price = Price(newPriceAmount)

        raisedEvents.add(
            PlanPriceChanged(id.toString(), oldPrice, this.price.amount)
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
