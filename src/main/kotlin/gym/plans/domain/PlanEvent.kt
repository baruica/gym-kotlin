package gym.plans.domain

sealed class PlanEvent(open val planId: String) {

    data class NewPlanCreated(
        override val planId: String,
        val planPrice: Int,
        val planDurationInMonths: Int
    ) : PlanEvent(planId)

    data class PlanPriceChanged(
        override val planId: String,
        val oldPrice: Int,
        val newPrice: Int
    ) : PlanEvent(planId)
}
