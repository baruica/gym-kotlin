package gym.membership.use_cases

import gym.DomainEvent
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import gym.subscriptions.domain.SubscriptionEvent.NewSubscription
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

class NewSubscriptionEventListener(
    private val memberRepository: MemberRepository
) {
    fun handle(event: NewSubscription): List<DomainEvent> {

        val email = EmailAddress(event.email)
        val knownMember: Member? = memberRepository.findByEmail(email)

        if (knownMember == null) {
            val member = Member(
                memberRepository.nextId(),
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
