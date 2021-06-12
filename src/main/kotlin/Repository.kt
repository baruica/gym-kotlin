interface Repository<T> {

    fun nextId(): String

    fun store(item: T)

    fun storeAll(items: List<T>)

    fun get(itemId: String): T
}
