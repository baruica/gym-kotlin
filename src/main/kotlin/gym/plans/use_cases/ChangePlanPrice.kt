package gym.plans.use_cases

import gym.plans.domain.Plan
import gym.plans.domain.PlanEvent
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository

class ChangePlanPrice(private val planRepository: PlanRepository) {

    fun handle(command: ChangePriceOfPlanCommand): List<PlanEvent> {

        val plan: Plan = planRepository.get(PlanId(command.planId))

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan.raisedEvents
    }
}
