package gym.membership.domain

import HasAnId
import java.time.LocalDate

@JvmInline
value class MemberId(private val id: String) {
    override fun toString(): String = id
}

class Member private constructor(
    val id: MemberId,
    val emailAddress: EmailAddress,
    private val memberSince: LocalDate,
) : HasAnId {
    private var welcomeEmailWasSent = false
    private var threeYearsAnniversaryThankYouEmailWasSent = false

    companion object {
        fun register(
            id: String,
            emailAddress: EmailAddress,
            memberSince: LocalDate
        ): Member {
            return Member(
                MemberId(id),
                emailAddress,
                memberSince
            )
        }
    }

    override fun getId(): String {
        return id.toString()
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
