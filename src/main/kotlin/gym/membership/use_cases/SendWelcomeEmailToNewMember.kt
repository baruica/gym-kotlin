package gym.membership.use_cases

import common.DomainEvent
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository

class SendWelcomeEmailToNewMember(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(command: SendWelcomeEmailToNewMemberCommand): List<DomainEvent> {

        val member = memberRepository.get(MemberId(command.memberId)) as Member

        mailer.sendEmail(
            member.emailAddress,
            "Thank you for subscribing ${member.emailAddress} !"
        )

        member.markWelcomeEmailAsSent()

        memberRepository.store(member)

        return member.raisedEvents
    }
}
