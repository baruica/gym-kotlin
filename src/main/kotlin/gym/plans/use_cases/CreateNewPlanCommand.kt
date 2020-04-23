package gym.plans.use_cases

import gym.plans.domain.PlanId

data class CreateNewPlanCommand(
    val planId: PlanId,
    val planPrice: Int,
    val planDurationInMonths: Int
)
