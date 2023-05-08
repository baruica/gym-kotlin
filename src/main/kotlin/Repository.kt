interface Repository<ID, A: Identifiable<ID>> {

    fun nextId(): String

    fun store(aggregate: A)

    fun storeAll(aggregates: List<A>)

    fun get(id: Id<ID>): A
}
