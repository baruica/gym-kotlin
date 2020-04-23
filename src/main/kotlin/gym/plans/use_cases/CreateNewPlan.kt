package gym.plans.use_cases

import gym.plans.domain.NewPlanCreated
import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

class CreateNewPlan(private val planRepository: PlanRepository) {

    fun handle(command: CreateNewPlanCommand): NewPlanCreated {
        val newPlan = Plan.new(
            command.planId,
            command.planPrice,
            command.planDurationInMonths
        )

        planRepository.store(newPlan)

        return NewPlanCreated(newPlan)
    }
}
