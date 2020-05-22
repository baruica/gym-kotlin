package gym.membership.domain

import common.DomainEvent
import java.time.Instant

sealed class MemberEvent : DomainEvent {
    override fun aggregateId(): String = memberId
    override val created: Instant = Instant.now()

    abstract val memberId: String
}

data class NewMembership(
    override val memberId: String,
    val memberEmail: String,
    val subscriptionId: String,
    val memberSince: String
) : MemberEvent()

data class WelcomeEmailWasSentToNewMember(
    override val memberId: String,
    val email: String,
    val memberSince: String
) : MemberEvent()

data class ThreeYearsAnniversaryThankYouEmailSent(
    override val memberId: String,
    val memberSince: String
) : MemberEvent()
