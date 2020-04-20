package gym.membership.domain

data class NewMemberSubscribed(
    val memberId: MemberId,
    val memberEmail: EmailAddress
)
