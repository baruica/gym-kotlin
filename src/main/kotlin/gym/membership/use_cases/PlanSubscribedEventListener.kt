package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import gym.membership.domain.NewMemberSubscribed
import gym.subscriptions.domain.PlanSubscribed

class PlanSubscribedEventListener(
    private val memberRepository: MemberRepository
) {
    fun handle(event: PlanSubscribed): NewMemberSubscribed? {

        var member: Member? = memberRepository.findByEmail(EmailAddress(event.email))

        if (member == null) {
            member = Member(
                memberRepository.nextId(),
                event.email,
                event.subscriptionId,
                event.subscriptionStartDate
            )
            memberRepository.store(member)

            return NewMemberSubscribed(member.id, member.email)
        }

        return null
    }
}
