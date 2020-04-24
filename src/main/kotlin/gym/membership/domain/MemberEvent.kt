package gym.membership.domain

sealed class MemberEvent(val aggregateId: String) {
    data class NewMemberSubscribed(val memberId: String, val memberEmail: String) : MemberEvent(memberId)
    data class WelcomeEmailWasSentToNewMember(val memberId: String) : MemberEvent(memberId)
    data class ThreeYearsAnniversaryThankYouEmailSent(val memberId: String) : MemberEvent(memberId)
}
