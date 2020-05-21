package common

interface AggregateId

interface Aggregate {
    val id: AggregateId
    val raisedEvents: MutableList<out DomainEvent>
}
