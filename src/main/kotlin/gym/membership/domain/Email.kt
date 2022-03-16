package gym.membership.domain

import java.time.LocalDate

sealed class Email(
    open val emailAddress: EmailAddress,
    val emailBody: String
) {
    data class Welcome(
        override val emailAddress: EmailAddress
    ) : Email(
        emailAddress,
        "Welcome to our gym :)"
    )

    data class SubscriptionSummary(
        override val emailAddress: EmailAddress,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val price: Int
    ) : Email(
        emailAddress,
        "Thank you for subscribing, this subscription will run from $startDate until $endDate, and will only cost you $price!"
    )

    data class ThreeYearsAnniversary(
        override val emailAddress: EmailAddress,
        val newSubscriptionPrice: Double
    ) : Email(
        emailAddress,
        "To thank you for your loyalty, we've applied a 5% discount on your subscription, you will now pay $newSubscriptionPrice !"
    )
}
