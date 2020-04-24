package gym.membership.use_cases

import gym.Event
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberId
import gym.membership.domain.MemberRepository

class SendWelcomeEmailToNewMember(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(event: Event.NewMemberSubscribed): List<Event> {

        val member: Member = memberRepository.get(MemberId(event.memberId))

        mailer.sendEmail(
            member.email,
            "Thank you for subscribing ${member.email} !"
        )

        member.markWelcomeEmailAsSent()

        memberRepository.store(member)

        return member.raisedEvents
    }
}
