package gym.plans.use_cases

import common.DomainEvent
import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository

class ChangePlanPrice(private val planRepository: PlanRepository) {

    fun handle(command: ChangePriceOfPlanCommand): List<DomainEvent> {

        val plan = planRepository.get(PlanId(command.planId)) as Plan

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan.raisedEvents
    }
}
