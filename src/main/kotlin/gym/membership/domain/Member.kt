package gym.membership.domain

import gym.membership.domain.MemberEvent.*
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

inline class MemberId(private val id: String) {
    override fun toString(): String = id
}

class Member(
    val id: MemberId,
    val email: Email,
    private val subscriptionId: SubscriptionId,
    private val memberSince: LocalDate
) {
    val raisedEvents: MutableList<MemberEvent> = mutableListOf()

    init {
        raisedEvents.add(
            NewMemberSubscribed(
                id.toString(),
                email.toString(),
                subscriptionId.toString(),
                memberSince.toString()
            )
        )
    }

    fun markWelcomeEmailAsSent() {
        raisedEvents.add(
            WelcomeEmailWasSentToNewMember(
                id.toString(),
                email.email,
                subscriptionId.toString()
            )
        )
    }

    fun isThreeYearsAnniversary(asOfDate: LocalDate): Boolean {
        return asOfDate.minusYears(3).isEqual(memberSince)
    }

    fun mark3YearsAnniversaryThankYouEmailAsSent() {
        raisedEvents.add(
            ThreeYearsAnniversaryThankYouEmailSent(
                id.toString(),
                memberSince.toString()
            )
        )
    }
}
