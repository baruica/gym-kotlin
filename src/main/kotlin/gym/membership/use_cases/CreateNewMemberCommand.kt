package gym.membership.use_cases

data class CreateNewMemberCommand(
    val subscriptionId: String,
    val subscriptionStartDate: String,
    val email: String
)
