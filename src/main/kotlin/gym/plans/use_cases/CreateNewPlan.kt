package gym.plans.use_cases

import gym.Event
import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

class CreateNewPlan(private val planRepository: PlanRepository) {

    fun handle(command: CreateNewPlanCommand): List<Event> {

        val newPlan = Plan.new(
            command.planId,
            command.planPrice,
            command.planDurationInMonths
        )

        planRepository.store(newPlan)

        return newPlan.raisedEvents
    }
}
