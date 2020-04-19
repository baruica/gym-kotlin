package fr.craft.gym.membership.domain

import fr.craft.gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

inline class MemberId(val id: String)

class Member(
    val id: MemberId,
    email: String,
    val subscriptionId: SubscriptionId,
    private val startDate: LocalDate
) {
    val email: EmailAddress = EmailAddress(email)

    private var welcomeEmailHasBeenSent: Boolean = false
    private var threeYearsAnniversaryEmailHasBeenSent: Boolean = false

    fun markWelcomeEmailAsSent() {
        this.welcomeEmailHasBeenSent = true
    }

    fun isThreeYearsAnniversary(asOfDate: LocalDate): Boolean {
        return asOfDate.minusYears(3).isEqual(startDate)
    }

    fun mark3YearsAnniversaryThankYouEmailAsSent() {
        this.threeYearsAnniversaryEmailHasBeenSent = true
    }
}
