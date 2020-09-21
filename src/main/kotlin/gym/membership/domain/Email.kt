package gym.membership.domain

data class Email internal constructor(val emailAddress: EmailAddress, val emailBody: String) {
    companion object {
        fun welcome(emailAddress: EmailAddress): Email {
            return Email(
                emailAddress,
                "Thank you for subscribing $emailAddress !"
            )
        }

        fun threeYearsAnniversary(emailAddress: EmailAddress): Email {
            return Email(
                emailAddress,
                "Thank you for your loyalty $emailAddress !"
            )
        }
    }
}
