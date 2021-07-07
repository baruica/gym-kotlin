interface Repository<T> {

    fun nextId(): String

    fun store(aggregate: T)

    fun storeAll(aggregates: List<T>)

    fun get(aggregateId: String): T
}
