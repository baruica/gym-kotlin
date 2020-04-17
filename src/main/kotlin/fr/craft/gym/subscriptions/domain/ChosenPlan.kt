package fr.craft.gym.subscriptions.domain

import fr.craft.gym.plans.domain.PlanId

data class ChosenPlan(
    val planId: PlanId,
    val price: Int,
    val durationInMonths: Int,
    val description: String
) {
    internal fun isYearly(): Boolean {
        return durationInMonths == 12
    }
}
