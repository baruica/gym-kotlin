package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.PlanId

data class ChangePriceOfPlanCommand(val planId: PlanId, val newPrice: Int)
