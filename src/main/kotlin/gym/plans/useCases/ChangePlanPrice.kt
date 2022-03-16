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

            val plan = planRepository.get(command.planId.toString())

            plan.changePrice(command.newPrice)

            planRepository.store(plan)

            return plan
        }
    }
}
