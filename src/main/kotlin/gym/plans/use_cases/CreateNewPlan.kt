package gym.plans.use_cases

import common.DomainEvent
import common.Repository
import gym.plans.domain.Plan
import gym.plans.domain.PlanId

class CreateNewPlan(private val planRepository: Repository) {

    fun handle(command: CreateNewPlanCommand): List<DomainEvent> {

        val newPlan = Plan(
            PlanId(planRepository.nextId()),
            command.planPrice,
            command.planDurationInMonths
        )

        planRepository.store(newPlan)

        return newPlan.raisedEvents
    }
}
