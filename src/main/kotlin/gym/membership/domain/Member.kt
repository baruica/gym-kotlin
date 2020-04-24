package gym.membership.domain

import gym.Aggregate
import gym.NewMemberSubscribed
import gym.ThreeYearsAnniversaryThankYouEmailSent
import gym.WelcomeEmailWasSentToNewMember
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
) : Aggregate(memberId.toString()) {

    val email = EmailAddress(email)

    init {
        raisedEvents.add(
            NewMemberSubscribed(memberId.toString(), this.email.toString())
        )
    }

    fun markWelcomeEmailAsSent() {
        raisedEvents.add(WelcomeEmailWasSentToNewMember(memberId.toString()))
    }

    fun isThreeYearsAnniversary(asOfDate: LocalDate): Boolean {
        return asOfDate.minusYears(3).isEqual(startDate)
    }

    fun mark3YearsAnniversaryThankYouEmailAsSent() {
        raisedEvents.add(ThreeYearsAnniversaryThankYouEmailSent(memberId.toString()))
    }
}
