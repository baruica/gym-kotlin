package gym.membership.domain

interface Mailer {

    fun sendWelcomeEmail(member: Member)

    fun sendSubscriptionSummary(
        emailAddress: EmailAddress,
        startDate: String,
        endDate: String,
        price: Int
    )

    fun send3YearsAnniversaryEmail(
        member: Member,
        newSubscriptionPrice: Double,
    )
}
