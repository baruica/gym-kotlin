package fr.craft.gym.membership.domain

interface Mailer {

    fun sendEmail(email: EmailAddress, message: String)
}
