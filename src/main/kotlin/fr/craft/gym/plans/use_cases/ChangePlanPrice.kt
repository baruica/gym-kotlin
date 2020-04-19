package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.Plan
import fr.craft.gym.plans.domain.PlanPriceChanged
import fr.craft.gym.plans.domain.PlanRepository

class ChangePlanPrice(private val planRepository: PlanRepository) {

    fun handle(command: ChangePriceOfPlanCommand): PlanPriceChanged {
        val plan: Plan = planRepository.get(command.planId)

        plan.changePrice(command.newPrice)

        planRepository.store(plan)

        return PlanPriceChanged(plan)
    }
}
