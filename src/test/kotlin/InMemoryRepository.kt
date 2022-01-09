import com.github.guepardoapps.kulid.ULID

abstract class InMemoryRepository<AGGREGATE : Aggregate>(
    protected val aggregates: MutableMap<String, AGGREGATE> = HashMap()
) : Repository<AGGREGATE> {

    override fun nextId(): String = ULID.random()

    override fun store(aggregate: AGGREGATE) {
        aggregates[aggregate.getId()] = aggregate
    }

    override fun storeAll(aggregates: List<AGGREGATE>) {
        aggregates.forEach { aggregate: AGGREGATE -> store(aggregate) }
    }

    override fun get(aggregateId: String): AGGREGATE {
        return aggregates[aggregateId]
            ?: throw NoSuchElementException("$aggregateId not found")
    }
}
