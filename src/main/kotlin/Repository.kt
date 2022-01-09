interface Repository<AGGREGATE> {

    fun nextId(): String

    fun store(aggregate: AGGREGATE)

    fun storeAll(aggregates: List<AGGREGATE>)

    fun get(aggregateId: String): AGGREGATE
}
