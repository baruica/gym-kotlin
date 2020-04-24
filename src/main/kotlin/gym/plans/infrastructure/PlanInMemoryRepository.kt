package gym.plans.infrastructure

import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository
import gym.plans.domain.PlanRepositoryException
import java.util.*

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
