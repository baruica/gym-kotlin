package gym.membership.infrastructure

import gym.membership.domain.Email
import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class InMemoryMailer : Mailer {

    private val sentEmails = HashMap<String, Email>()

    override fun sendWelcomeEmail(member: Member) {
        sentEmails[UUID.randomUUID().toString()] = Email.welcome(member.emailAddress)
        member.markWelcomeEmailAsSent()
    }

    override fun send3YearsAnniversaryEmail(member: Member) {
        sentEmails[UUID.randomUUID().toString()] = Email.threeYearsAnniversary(member.emailAddress)
        member.mark3YearsAnniversaryThankYouEmailAsSent()
    }

    internal fun welcomeEmailWasSentTo(emailAddress: String): Boolean {
        return sentEmails.containsValue(
            Email.welcome(EmailAddress(emailAddress))
        )
    }

    internal fun threeYearsAnniversaryWasSentTo(emailAddress: String): Boolean {
        return sentEmails.containsValue(
            Email.threeYearsAnniversary(EmailAddress(emailAddress))
        )
    }
}
