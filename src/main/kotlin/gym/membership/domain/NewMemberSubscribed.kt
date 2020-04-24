package gym.membership.domain

class NewMemberSubscribed(
    memberId: MemberId,
    memberEmail: EmailAddress
) {
    val memberId = memberId.toString()
    val memberEmail = memberEmail.toString()
}
