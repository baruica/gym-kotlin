package gym.membership.use_cases

import common.DomainEvent
import gym.membership.domain.Email
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository
import gym.subscriptions.domain.NewSubscription
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

class NewSubscriptionEventListener(
    private val memberRepository: MemberRepository
) {
    fun handle(event: NewSubscription): List<DomainEvent> {

        val email = Email(event.email)
        val knownMember: Member? = memberRepository.findByEmail(email)

        if (knownMember == null) {
            val member = Member(
                MemberId(memberRepository.nextId()),
                email,
                SubscriptionId(event.subscriptionId),
                LocalDate.parse(event.subscriptionStartDate)
            )
            memberRepository.store(member)

            return member.raisedEvents
        }

        return listOf()
    }
}
