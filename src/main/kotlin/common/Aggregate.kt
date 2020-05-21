package common

interface Aggregate {
    val raisedEvents: MutableList<out DomainEvent>
}
