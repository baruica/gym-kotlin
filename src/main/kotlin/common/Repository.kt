package common

interface Repository {
    fun nextId(): String

    fun store(aggregate: Aggregate)

    fun storeAll(aggregates: Map<out AggregateId, Aggregate>)

    fun get(aggregateId: AggregateId): Aggregate
}
