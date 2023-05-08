@JvmInline
value class Id<T>(val value: T)

interface Identifiable<T> {
    val id: Id<T>
}
