package gym.membership.use_cases

data class RegisterNewMemberCommand(
    val memberId: String,
    val subscriptionId: String,
    val subscriptionStartDate: String,
    val email: String
)
