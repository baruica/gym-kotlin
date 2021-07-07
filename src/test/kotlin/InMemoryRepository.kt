import java.util.*

abstract class InMemoryRepository<T : Aggregate>(
    protected val aggregates: MutableMap<String, T> = HashMap()
) : Repository<T> {

    override fun nextId(): String = UUID.randomUUID().toString()

    override fun store(aggregate: T) {
        aggregates[aggregate.getId()] = aggregate
    }

    override fun storeAll(aggregates: List<T>) {
        aggregates.forEach { aggregate: T -> store(aggregate) }
    }

    override fun get(aggregateId: String): T {
        return aggregates[aggregateId]
            ?: throw RuntimeException("$aggregateId not found")
    }
}
