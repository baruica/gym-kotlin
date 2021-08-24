package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

class CreateNewPlan(private val planRepository: PlanRepository) {

    operator fun invoke(command: CreateNewPlanCommand): Plan {

        val newPlan = Plan.new(
            command.planId,
            command.planPrice,
            command.planDurationInMonths
        )
        planRepository.store(newPlan)

        return newPlan
    }
}
