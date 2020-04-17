package fr.craft.gym.plans.infrastructure

import fr.craft.gym.plans.domain.Plan
import fr.craft.gym.plans.domain.PlanId
import fr.craft.gym.plans.domain.PlanRepository
import fr.craft.gym.plans.domain.PlanRepositoryException
import java.util.HashMap
import java.util.UUID

class PlanInMemoryRepository : PlanRepository {

    private val plans = HashMap<PlanId, Plan>()

    override fun nextId(): PlanId {
        return PlanId(UUID.randomUUID().toString())
    }

    override fun store(plan: Plan) {
        plans[plan.id] = plan
    }

    override fun get(planId: PlanId): Plan {
        return plans[planId]
            ?: throw PlanRepositoryException.notFound(planId)
    }
}
