package fr.craft.gym

import fr.craft.gym.membership.domain.EmailAddress

interface Mailer {

    fun sendEmail(email: EmailAddress, message: String)
}
