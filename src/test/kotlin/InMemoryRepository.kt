import com.github.guepardoapps.kulid.ULID

abstract class InMemoryRepository<ID, A : Identifiable<ID>>(
    internal val aggregates: MutableMap<Id<ID>, A> = HashMap()
) : Repository<ID, A> {

    override fun nextId(): String = ULID.random()

    override fun store(aggregate: A) {
        aggregates[aggregate.id] = aggregate
    }

    override fun storeAll(aggregates: List<A>) {
        aggregates.forEach { aggregate: A -> store(aggregate) }
    }

    override fun get(id: Id<ID>): A {
        return aggregates[id]
            ?: throw NoSuchElementException("$id not found")
    }
}
