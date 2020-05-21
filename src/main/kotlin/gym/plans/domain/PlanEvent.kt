package gym.plans.domain

import common.DomainEvent

sealed class PlanEvent(override val aggregateId: String) : DomainEvent

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
