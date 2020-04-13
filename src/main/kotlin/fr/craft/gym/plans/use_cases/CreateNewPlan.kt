package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.plans.domain.PlanPeriodicity

data class CreateNewPlan(
    val planId: PlanId,
    val basePrice: Int,
    val planPeriodicity: PlanPeriodicity
)
