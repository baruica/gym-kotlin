package gym.membership.useCases

data class SendSummaryUponSubscriptionCommand(
    val email: String,
    val startDate: String,
    val endDate: String,
    val price: Int
)
