package fr.craft.gym.plans.domain

interface PlanRepository {

    fun nextId(): PlanId

    fun store(plan: Plan)

    fun get(planId: PlanId): Plan
}
