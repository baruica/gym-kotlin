package gym.membership.useCases

data class Send3YearsAnniversaryThankYouEmailsCommand(
    val memberId: String,
    val newSubscriptionPrice: Double,
)
