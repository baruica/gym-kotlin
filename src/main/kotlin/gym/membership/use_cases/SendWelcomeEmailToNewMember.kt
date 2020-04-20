package gym.membership.use_cases

import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import gym.membership.domain.NewMemberSubscribed
import gym.membership.domain.WelcomeEmailWasSentToNewMember

class SendWelcomeEmailToNewMember(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(event: NewMemberSubscribed): WelcomeEmailWasSentToNewMember {

        val member: Member = memberRepository.get(event.memberId)

        mailer.sendEmail(
            member.email,
            "Thank you for subscribing ${member.email} !"
        )

        member.markWelcomeEmailAsSent()

        memberRepository.store(member)

        return WelcomeEmailWasSentToNewMember(member.id)
    }
}
