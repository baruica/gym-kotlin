package gym.membership.domain

import gym.DomainEvent

sealed class MemberEvent(id: String) : DomainEvent(id) {

    data class NewMemberSubscribed(
        val memberId: String,
        val memberEmail: String,
        val subscriptionId: String,
        val memberSince: String
    ) : MemberEvent(memberId)

    data class WelcomeEmailWasSentToNewMember(
        val memberId: String,
        val email: String,
        val memberSince: String
    ) : MemberEvent(memberId)

    data class ThreeYearsAnniversaryThankYouEmailSent(
        val memberId: String,
        val memberSince: String
    ) : MemberEvent(memberId)
}
