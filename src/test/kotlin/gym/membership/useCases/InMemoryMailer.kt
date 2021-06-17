package gym.membership.useCases

import gym.membership.domain.Email
import gym.membership.domain.Email.*
import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import java.util.*
import kotlin.collections.set

class InMemoryMailer(
    private val sentEmails: HashMap<String, Email> = HashMap<String, Email>()
) : Mailer {

    override fun sendWelcomeEmail(member: Member) {
        sentEmails[UUID.randomUUID().toString()] = Welcome(member.emailAddress)
        member.markWelcomeEmailAsSent()
    }

    override fun sendSubscriptionSummary(emailAddress: EmailAddress, startDate: String, endDate: String, price: Int) {
        sentEmails[UUID.randomUUID().toString()] = SubscriptionSummary(emailAddress, startDate, endDate, price)
    }

    override fun send3YearsAnniversaryEmail(member: Member, newSubscriptionPrice: Double) {
        sentEmails[UUID.randomUUID().toString()] = ThreeYearsAnniversary(member.emailAddress, newSubscriptionPrice)
        member.mark3YearsAnniversaryThankYouEmailAsSent()
    }

    internal fun welcomeEmailWasSentTo(emailAddress: EmailAddress): Boolean {
        return sentEmails.containsValue(
            Welcome(emailAddress)
        )
    }

    internal fun subscriptionSummaryEmailWasSentTo(
        emailAddress: EmailAddress,
        startDate: String,
        endDate: String,
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
