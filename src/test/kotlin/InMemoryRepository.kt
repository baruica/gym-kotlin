import java.util.*

abstract class InMemoryRepository<T : HasAnId> : Repository<T> {

    protected val items: MutableMap<String, T> = HashMap()

    override fun nextId(): String {
        return UUID.randomUUID().toString()
    }

    override fun store(item: T) {
        items[item.getId()] = item
    }

    override fun storeAll(items: List<T>) {
        items.forEach { item: T -> store(item) }
    }

    override fun get(itemId: String): T {
        return items[itemId]
            ?: throw RuntimeException("$itemId not found")
    }
}
