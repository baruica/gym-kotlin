package common

import java.util.*

abstract class InMemoryRepository : Repository {

    val aggregates: MutableMap<AggregateId, Aggregate> = mutableMapOf()

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
