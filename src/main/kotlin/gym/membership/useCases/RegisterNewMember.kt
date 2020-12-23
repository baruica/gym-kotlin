package gym.membership.useCases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import java.time.LocalDate

class RegisterNewMember(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(command: RegisterNewMemberCommand): Member? {

        val emailAddress = EmailAddress(command.email)
        val knownMember: Member? = memberRepository.findByEmailAddress(emailAddress)

        if (knownMember == null) {
            val member = Member.register(
                command.memberId,
                emailAddress,
                LocalDate.parse(command.subscriptionStartDate)
            )

            mailer.sendWelcomeEmail(member)

            memberRepository.store(member)

            return member
        }

        return null
    }
}
