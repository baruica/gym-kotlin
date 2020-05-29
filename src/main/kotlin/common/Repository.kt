package common

interface Repository {
    fun nextId(): String

    fun store(aggregate: Aggregate)

    fun storeAll(aggregates: List<Aggregate>)

    fun get(aggregateId: AggregateId): Aggregate
}
