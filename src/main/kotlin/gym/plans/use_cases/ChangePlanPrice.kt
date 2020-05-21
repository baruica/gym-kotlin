package gym.plans.use_cases

import common.DomainEvent
import common.Repository
import gym.plans.domain.Plan
import gym.plans.domain.PlanId

class ChangePlanPrice(private val planRepository: Repository) {

    fun handle(command: ChangePriceOfPlanCommand): List<DomainEvent> {

        val plan = planRepository.get(PlanId(command.planId)) as Plan

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan.raisedEvents
    }
}
