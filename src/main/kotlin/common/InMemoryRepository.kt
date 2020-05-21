package common

import java.util.*

open class InMemoryRepository : Repository {

    val aggregates = HashMap<AggregateId, Aggregate>()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun store(aggregate: Aggregate) {
        aggregates[aggregate.id] = aggregate
    }

    override fun storeAll(aggregates: Map<out AggregateId, Aggregate>) {
        aggregates.forEach {
            store(it.value)
        }
    }

    override fun get(aggregateId: AggregateId): Aggregate {
        return aggregates[aggregateId]
            ?: throw RepositoryException.notFound(aggregateId)
    }
}
