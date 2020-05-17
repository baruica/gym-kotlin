package gym.membership.domain

interface Mailer {

    fun sendEmail(email: Email, message: String)
}
