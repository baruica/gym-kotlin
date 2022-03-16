package gym.membership.domain

import Aggregate
import java.time.LocalDate

@JvmInline
value class MemberId(private val value: String) {
    override fun toString(): String = value
}

class Member private constructor(
    val id: MemberId,
    val emailAddress: EmailAddress,
    private val memberSince: LocalDate,
) : Aggregate {
    private var welcomeEmailWasSent = false
    private var threeYearsAnniversaryThankYouEmailWasSent = false

    override fun getId(): String = id.toString()

    companion object {
        fun register(
            id: MemberId,
            emailAddress: EmailAddress,
            memberSince: LocalDate
        ): Member {
            return Member(
                id,
                emailAddress,
                memberSince
            )
        }
    }

    fun markWelcomeEmailAsSent() {
        welcomeEmailWasSent = true
    }

    fun isThreeYearsAnniversary(date: LocalDate): Boolean {
        return date.minusYears(3).isEqual(memberSince)
    }

    fun mark3YearsAnniversaryThankYouEmailAsSent() {
        threeYearsAnniversaryThankYouEmailWasSent = true
    }
}
