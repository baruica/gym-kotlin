package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

data class ChangePlanPrice(
    val planId: String,
    val newPrice: Int,
)

class ChangePlanPriceHandler(private val planRepository: PlanRepository) {

    operator fun invoke(command: ChangePlanPrice): Plan {

        val plan = planRepository.get(command.planId)

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan
    }
}
