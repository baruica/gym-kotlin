package gym.membership.use_cases

import gym.membership.domain.Email
import gym.membership.domain.Member
import gym.membership.domain.MemberEvent
import gym.membership.domain.MemberRepository
import gym.subscriptions.domain.SubscriptionEvent.NewSubscription
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

class NewSubscriptionEventListener(
    private val memberRepository: MemberRepository
) {
    fun handle(event: NewSubscription): List<MemberEvent> {

        val email = Email(event.email)
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
