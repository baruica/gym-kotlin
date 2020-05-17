package gym.membership.infrastructure

import gym.membership.domain.Email
import gym.membership.domain.Mailer
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.set

class InMemoryMailer : Mailer {

    val sentEmails = HashMap<String, String>()

    override fun sendEmail(email: Email, message: String) {
        sentEmails[UUID.randomUUID().toString()] = message
    }
}
