package gym.membership.useCases

data class RegisterNewMemberCommand(
    val memberId: String,
    val subscriptionId: String,
    val subscriptionStartDate: String,
    val email: String
)
