package gym.membership.domain

sealed class Email(open val emailAddress: EmailAddress, val emailBody: String) {

    data class Welcome(override val emailAddress: EmailAddress) : Email(
        emailAddress,
        "Welcome to our gym :)"
    )

    data class SubscriptionSummary(
        override val emailAddress: EmailAddress,
        val startDate: String,
        val endDate: String,
        val price: Int
    ) : Email(
        emailAddress,
        "Thank you for subscribing, this subscription will run from $startDate until $endDate, and will only cost you $price!"
    )

    data class ThreeYearsAnniversary(override val emailAddress: EmailAddress) : Email(
        emailAddress,
        "Thank you for your loyalty $emailAddress !"
    )
}
