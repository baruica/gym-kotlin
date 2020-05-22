package gym.plans.infrastructure

import common.Aggregate
import common.AggregateId
import common.RepositoryException
import gym.plans.domain.Plan
import gym.plans.domain.PlanId
import gym.plans.domain.PlanRepository
import java.util.*

class PlanInMemoryRepository : PlanRepository {

    private val plans = mutableMapOf<PlanId, Plan>()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun store(aggregate: Aggregate) {
        plans[aggregate.id as PlanId] = aggregate as Plan
    }

    override fun storeAll(aggregates: Map<out AggregateId, Aggregate>) {
        aggregates.forEach {
            store(it.value)
        }
    }

    override fun get(aggregateId: AggregateId): Aggregate {
        return plans[aggregateId]
            ?: throw RepositoryException.notFound(aggregateId)
    }
}
