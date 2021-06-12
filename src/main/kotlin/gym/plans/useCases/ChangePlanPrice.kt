package gym.plans.useCases

import gym.plans.domain.Plan
import gym.plans.domain.PlanRepository

class ChangePlanPrice(private val planRepository: PlanRepository) {

    fun handle(command: ChangePriceOfPlanCommand): Plan {

        val plan = planRepository.get(command.planId)

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return plan
    }
}
