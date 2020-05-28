package gym.plans.use_cases

import common.DomainEvent
import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository

class CreateNewPlan(private val planRepository: PlanRepository) {

    fun handle(command: CreateNewPlanCommand): List<DomainEvent> {

        val newPlan = Plan.new(
            PlanId(planRepository.nextId()),
            command.planPrice,
            command.planDurationInMonths
        )

        planRepository.store(newPlan)

        return newPlan.raisedEvents
    }
}
