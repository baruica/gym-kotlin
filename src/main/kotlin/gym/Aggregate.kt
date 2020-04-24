package gym

open class Aggregate(val aggregateId: String) {
    val raisedEvents: MutableList<Event> = mutableListOf()
}
