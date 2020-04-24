package gym.plans.domain

sealed class PlanEvent(val aggregateId: String) {
    data class NewPlanCreated(val planId: String) : PlanEvent(planId)
    data class PlanPriceChanged(val planId: String) : PlanEvent(planId)
}
