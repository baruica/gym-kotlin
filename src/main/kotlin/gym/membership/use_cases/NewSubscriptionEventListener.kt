package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberEvent
import gym.membership.domain.MemberRepository
import gym.subscriptions.domain.SubscriptionEvent
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

class NewSubscriptionEventListener(
    private val memberRepository: MemberRepository
) {
    fun handle(event: SubscriptionEvent.NewSubscription): List<MemberEvent> {

        val knownMember: Member? = memberRepository.findByEmail(EmailAddress(event.email))

        if (knownMember == null) {
            val member = Member(
                memberRepository.nextId(),
                event.email,
                SubscriptionId(event.subscriptionId),
                LocalDate.parse(event.subscriptionStartDate)
            )
            memberRepository.store(member)

            return member.raisedEvents
        }

        return listOf()
    }
}
