package common

import java.time.Instant

interface DomainEvent {
    val created: Instant

    fun aggregateId(): String
}
