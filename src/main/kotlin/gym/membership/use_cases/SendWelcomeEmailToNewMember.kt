package gym.membership.use_cases

import common.DomainEvent
import gym.membership.domain.*

class SendWelcomeEmailToNewMember(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(event: NewMemberSubscribed): List<DomainEvent> {

        val member = memberRepository.get(MemberId(event.memberId)) as Member

        mailer.sendEmail(
            member.email,
            "Thank you for subscribing ${member.email} !"
        )

        member.markWelcomeEmailAsSent()

        memberRepository.store(member)

        return member.raisedEvents
    }
}
