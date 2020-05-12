package gym.membership.domain

sealed class MemberEvent(open val memberId: String) {

    data class NewMemberSubscribed(
        override val memberId: String,
        val memberEmail: String,
        val subscriptionId: String,
        val memberSince: String
    ) : MemberEvent(memberId)

    data class WelcomeEmailWasSentToNewMember(
        override val memberId: String,
        val email: String,
        val memberSince: String
    ) : MemberEvent(memberId)

    data class ThreeYearsAnniversaryThankYouEmailSent(
        override val memberId: String,
        val memberSince: String
    ) : MemberEvent(memberId)
}
