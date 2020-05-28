package gym.membership.use_cases

data class RegisterNewMemberCommand(
    val subscriptionId: String,
    val subscriptionStartDate: String,
    val email: String
)
