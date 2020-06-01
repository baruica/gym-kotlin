package gym.subscriptions.use_cases

data class SubscribeToPlanCommand(
    val subscriptionId: String,
    val planPrice: Int,
    val planDurationInMonths: Int,
    val startDate: String,
    val isStudent: Boolean,
    val email: String
)
