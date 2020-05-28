package gym.membership.domain

import common.Aggregate
import common.AggregateId
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

inline class MemberId(private val id: String) : AggregateId {
    override fun toString(): String = id
}

class Member private constructor(
    override val id: MemberId,
    val emailAddress: EmailAddress,
    private val subscriptionId: SubscriptionId,
    private val memberSince: LocalDate
) : Aggregate {
    override val raisedEvents = mutableListOf<MemberEvent>()

    companion object {
        fun register(
            id: MemberId,
            emailAddress: EmailAddress,
            subscriptionId: SubscriptionId,
            memberSince: LocalDate
        ): Member {
            val member = Member(
                id,
                emailAddress,
                subscriptionId,
                memberSince
            )

            member.raisedEvents.add(
                NewMemberRegistered(
                    member.id.toString(),
                    member.emailAddress.toString(),
                    member.subscriptionId.toString(),
                    member.memberSince.toString()
                )
            )

            return member
        }
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
