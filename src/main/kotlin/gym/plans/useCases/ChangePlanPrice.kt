package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

data class ChangePlanPriceCommand(
    val planId: String,
    val newPrice: Int,
)

class ChangePlanPrice(private val planRepository: PlanRepository) {

    operator fun invoke(command: ChangePlanPriceCommand): Plan {

        val plan = planRepository.get(command.planId)

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan
    }
}
