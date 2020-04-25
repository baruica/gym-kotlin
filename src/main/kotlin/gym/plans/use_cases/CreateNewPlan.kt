package gym.plans.use_cases

import gym.plans.domain.Plan
import gym.plans.domain.PlanEvent
import gym.plans.domain.PlanRepository

class CreateNewPlan(private val planRepository: PlanRepository) {

    fun handle(command: CreateNewPlanCommand): List<PlanEvent> {

        val newPlan = Plan(
            planRepository.nextId(),
            command.planPrice,
            command.planDurationInMonths
        )

        planRepository.store(newPlan)

        return newPlan.raisedEvents
    }
}
