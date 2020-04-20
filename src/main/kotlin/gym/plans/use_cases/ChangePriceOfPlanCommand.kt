package gym.plans.use_cases

import gym.plans.domain.PlanId

data class ChangePriceOfPlanCommand(val planId: PlanId, val newPrice: Int)
