package gym.membership.domain

import common.Aggregate
import common.AggregateId
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

inline class MemberId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Member(
    override val id: MemberId,
    val emailAddress: EmailAddress,
    private val subscriptionId: SubscriptionId,
    private val memberSince: LocalDate
) : Aggregate {
    override val raisedEvents = mutableListOf<MemberEvent>()

    init {
        raisedEvents.add(
            NewMembership(
                id.toString(),
                emailAddress.toString(),
                subscriptionId.toString(),
                memberSince.toString()
            )
        )
    }

    fun markWelcomeEmailAsSent() {
        raisedEvents.add(
            WelcomeEmailWasSentToNewMember(
                id.toString(),
                emailAddress.value,
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
