package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository

data class CreateNewPlan(
    val planId: PlanId,
    val planPrice: Int,
    val planDurationInMonths: Int,
) {
    class Handler(private val planRepository: PlanRepository) {

        operator fun invoke(command: CreateNewPlan): Plan {

            return Plan.new(
                command.planId,
                command.planPrice,
                command.planDurationInMonths
            ).also {
                planRepository.store(it)
            }
        }
    }
}
