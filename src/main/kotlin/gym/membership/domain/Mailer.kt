package gym.membership.domain

import java.time.LocalDate

interface Mailer {

    fun sendWelcomeEmail(member: Member)

    fun sendSubscriptionSummary(
        emailAddress: EmailAddress,
        startDate: LocalDate,
        endDate: LocalDate,
        price: Int
    )

    fun send3YearsAnniversaryEmail(
        member: Member,
        newSubscriptionPrice: Double,
    )
}
