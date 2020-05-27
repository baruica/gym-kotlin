package gym.membership.domain

interface Mailer {

    fun sendEmail(emailAddress: EmailAddress, message: String)
}
