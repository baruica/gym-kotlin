package gym.membership.domain

class ThreeYearsAnniversaryThankYouEmailsSent(memberIds: List<MemberId>) {
    val memberIds: List<String> = memberIds.map { memberId -> memberId.toString() }
}
