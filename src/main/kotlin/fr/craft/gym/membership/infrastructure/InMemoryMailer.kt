package fr.craft.gym.membership.infrastructure

import fr.craft.gym.membership.domain.EmailAddress
import fr.craft.gym.membership.domain.Mailer
import java.util.UUID

class InMemoryMailer : Mailer {

    val sentEmails = HashMap<String, String>()

    override fun sendEmail(email: EmailAddress, message: String) {
        sentEmails[UUID.randomUUID().toString()] = message
    }
}
