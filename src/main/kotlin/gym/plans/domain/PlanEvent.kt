package gym.plans.domain

import gym.DomainEvent

sealed class PlanEvent(id: String) : DomainEvent(id) {

    data class NewPlanCreated(
        val planId: String,
        val planPrice: Int,
        val planDurationInMonths: Int
    ) : PlanEvent(planId)

    data class PlanPriceChanged(
        val planId: String,
        val oldPrice: Int,
        val newPrice: Int
    ) : PlanEvent(planId)
}
