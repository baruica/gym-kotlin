package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.plans.domain.PlanPeriodicity

data class CreateNewPlanCommand(
    val planId: PlanId,
    val basePrice: Int,
    val planPeriodicity: PlanPeriodicity
)
