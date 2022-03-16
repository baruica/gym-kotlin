package gym.membership.useCases

import com.github.guepardoapps.kulid.ULID
import gym.membership.domain.Email
import gym.membership.domain.Email.*
import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import java.time.LocalDate
import kotlin.collections.set

class InMemoryMailer(
    private val sentEmails: HashMap<String, Email> = HashMap()
) : Mailer {

    override fun sendWelcomeEmail(member: Member) {
        sentEmails[ULID.random()] = Welcome(member.emailAddress)
        member.markWelcomeEmailAsSent()
    }

    override fun sendSubscriptionSummary(emailAddress: EmailAddress, startDate: LocalDate, endDate: LocalDate, price: Int) {
        sentEmails[ULID.random()] = SubscriptionSummary(emailAddress, startDate, endDate, price)
    }

    override fun send3YearsAnniversaryEmail(member: Member, newSubscriptionPrice: Double) {
        sentEmails[ULID.random()] = ThreeYearsAnniversary(member.emailAddress, newSubscriptionPrice)
        member.mark3YearsAnniversaryThankYouEmailAsSent()
    }

    internal fun welcomeEmailWasSentTo(emailAddress: EmailAddress): Boolean {
        return sentEmails.containsValue(
            Welcome(emailAddress)
        )
    }

    internal fun subscriptionSummaryEmailWasSentTo(
        emailAddress: EmailAddress,
        startDate: LocalDate,
        endDate: LocalDate,
        price: Int
    ): Boolean {
        return sentEmails.containsValue(
            SubscriptionSummary(
                emailAddress,
                startDate,
                endDate,
                price
            )
        )
    }

    internal fun threeYearsAnniversaryWasSentTo(emailAddress: EmailAddress, newSubscriptionPrice: Double): Boolean {
        return sentEmails.containsValue(
            ThreeYearsAnniversary(emailAddress, newSubscriptionPrice)
        )
    }
}
