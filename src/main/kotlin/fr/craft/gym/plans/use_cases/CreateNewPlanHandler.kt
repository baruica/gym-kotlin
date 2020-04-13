package fr.craft.gym.plans.use_cases

import fr.craft.gym.plans.domain.NewPlanCreated
import fr.craft.gym.plans.domain.Plan
import fr.craft.gym.plans.domain.PlanRepository

class CreateNewPlanHandler(private val planRepository: PlanRepository) {

    fun handle(command: CreateNewPlan): NewPlanCreated {
        val newPlan = Plan.new(
            command.planId,
            command.basePrice,
            command.planPeriodicity
        )

        planRepository.store(newPlan)

        return NewPlanCreated(newPlan)
    }
}
