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
    private var welcomeEmailWasSent = false
    private var threeYearsAnniversaryThankYouEmailWasSent = false

    companion object {
        fun register(
            id: String,
            emailAddress: EmailAddress,
            subscriptionId: SubscriptionId,
            memberSince: LocalDate
        ): Member {
            return Member(
                MemberId(id),
                emailAddress,
                subscriptionId,
                memberSince
            )
        }
    }

    fun markWelcomeEmailAsSent() {
        welcomeEmailWasSent = true
    }

    fun isThreeYearsAnniversary(asOfDate: LocalDate): Boolean {
        return asOfDate.minusYears(3).isEqual(memberSince)
    }

    fun mark3YearsAnniversaryThankYouEmailAsSent() {
        threeYearsAnniversaryThankYouEmailWasSent = true
    }
}
