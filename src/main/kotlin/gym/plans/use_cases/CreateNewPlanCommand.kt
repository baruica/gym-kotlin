package gym.plans.use_cases

import gym.plans.domain.PlanId
import gym.plans.domain.PlanPeriodicity

data class CreateNewPlanCommand(
    val planId: PlanId,
    val basePrice: Int,
    val planPeriodicity: PlanPeriodicity
)
