package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository

data class ChangePlanPrice(
    val planId: PlanId,
    val newPrice: Int,
) {
    class Handler(private val planRepository: PlanRepository) {

        operator fun invoke(command: ChangePlanPrice): Plan {

            return planRepository.get(command.planId.toString())
                .let { it.changePrice(command.newPrice) }
                .also { planRepository.store(it) }
        }
    }
}
