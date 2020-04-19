package fr.craft.gym.membership.use_cases

import fr.craft.gym.membership.domain.Mailer
import fr.craft.gym.membership.domain.Member
import fr.craft.gym.membership.domain.MemberRepository
import fr.craft.gym.membership.domain.NewMemberSubscribed
import fr.craft.gym.membership.domain.WelcomeEmailWasSentToNewMember

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
