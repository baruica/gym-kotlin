package gym

abstract class Aggregate(val aggregateId: String) {
    val raisedEvents: MutableList<Event> = mutableListOf()
}
