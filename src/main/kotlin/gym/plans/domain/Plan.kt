package gym.plans.domain

import gym.plans.domain.PlanEvent.NewPlanCreated
import gym.plans.domain.PlanEvent.PlanPriceChanged

inline class PlanId(private val id: String) {
    override fun toString(): String {
        return id
    }
}

class Plan(id: String, priceAmount: Int, planDurationInMonths: Int) {

    val planId = PlanId(id)

    private val planDurationsInMonths = listOf(1, 12)

    internal var price = Price(priceAmount)

    val raisedEvents: MutableList<PlanEvent> = mutableListOf()

    init {
        require(planDurationsInMonths.contains(planDurationInMonths)) {
            "Plan duration is either 1 month or 12 months, was $planDurationInMonths"
        }

        raisedEvents.add(
            NewPlanCreated(planId.toString())
        )
    }

    fun changePrice(newPriceAmount: Int) {
        val oldPrice = this.price.priceAmount

        this.price = Price(newPriceAmount)

        raisedEvents.add(
            PlanPriceChanged(planId.toString(), oldPrice, this.price.priceAmount)
        )
    }
}

internal data class Price(val priceAmount: Int) {
    init {
        require(priceAmount >= 0) {
            "Price amount must be non-negative, was $priceAmount"
        }
    }
}
