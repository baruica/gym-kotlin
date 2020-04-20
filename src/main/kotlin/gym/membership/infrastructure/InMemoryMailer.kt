package gym.membership.infrastructure

import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import java.util.UUID

class InMemoryMailer : Mailer {

    val sentEmails = HashMap<String, String>()

    override fun sendEmail(email: EmailAddress, message: String) {
        sentEmails[UUID.randomUUID().toString()] = message
    }
}
