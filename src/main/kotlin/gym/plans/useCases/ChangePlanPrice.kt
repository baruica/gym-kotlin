package gym.plans.useCases

import Id
import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

data class ChangePlanPrice(
    val planId: Id<String>,
    val newPrice: Int,
) {
    class Handler(private val planRepository: PlanRepository) {

        operator fun invoke(command: ChangePlanPrice): Plan {

            return planRepository.get(command.planId)
                .changePrice(command.newPrice)
                .also { planRepository.store(it) }
        }
    }
}
