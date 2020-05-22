package gym.plans.domain

import common.DomainEvent
import java.time.Instant

sealed class PlanEvent : DomainEvent {
    override fun aggregateId(): String = planId
    override val created: Instant = Instant.now()

    abstract val planId: String
}

data class NewPlanCreated(
    override val planId: String,
    val planPrice: Int,
    val planDurationInMonths: Int
) : PlanEvent()

data class PlanPriceChanged(
    override val planId: String,
    val oldPrice: Int,
    val newPrice: Int
) : PlanEvent()
