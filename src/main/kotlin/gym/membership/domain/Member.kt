package gym.membership.domain

import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

inline class MemberId(private val id: String) {
    override fun toString(): String {
        return id
    }
}

class Member(
    val memberId: MemberId,
    email: String,
    val subscriptionId: SubscriptionId,
    private val startDate: LocalDate
) {
    val email = EmailAddress(email)

    val raisedEvents: MutableList<MemberEvent> = mutableListOf()

    init {
        raisedEvents.add(
            MemberEvent.NewMemberSubscribed(memberId.toString(), this.email.toString())
        )
    }

    fun markWelcomeEmailAsSent() {
        raisedEvents.add(
            MemberEvent.WelcomeEmailWasSentToNewMember(memberId.toString())
        )
    }

    fun isThreeYearsAnniversary(asOfDate: LocalDate): Boolean {
        return asOfDate.minusYears(3).isEqual(startDate)
    }

    fun mark3YearsAnniversaryThankYouEmailAsSent() {
        raisedEvents.add(
            MemberEvent.ThreeYearsAnniversaryThankYouEmailSent(memberId.toString())
        )
    }
}
