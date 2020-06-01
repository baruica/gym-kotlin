package gym.plans.use_cases

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository

class ChangePlanPrice(private val planRepository: PlanRepository) {

    fun handle(command: ChangePriceOfPlanCommand): Plan {

        val plan = planRepository.get(PlanId(command.planId)) as Plan

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan
    }
}
