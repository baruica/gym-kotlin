package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.PlanId

data class ChangePriceOfPlan(val planId: PlanId, val newPrice: Int)
