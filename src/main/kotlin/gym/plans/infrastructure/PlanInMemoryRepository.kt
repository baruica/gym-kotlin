package gym.plans.infrastructure

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository
import java.util.HashMap
import java.util.UUID

class PlanInMemoryRepository : PlanRepository {

    private val plans = HashMap<PlanId, Plan>()

    override fun nextId(): PlanId {
        return PlanId(UUID.randomUUID().toString())
    }

    override fun store(plan: Plan) {
        plans[plan.planId] = plan
    }

    override fun get(planId: PlanId): Plan {
        return plans[planId]
            ?: throw PlanRepositoryException.notFound(planId)
    }
}
