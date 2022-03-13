package gym.membership.useCases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import java.time.LocalDate

data class RegisterNewMember(
    val memberId: String,
    val subscriptionId: String,
    val subscriptionStartDate: LocalDate,
    val email: String,
)

class RegisterNewMemberHandler(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer,
) {
    operator fun invoke(command: RegisterNewMember): Member? {

        val emailAddress = EmailAddress(command.email)
        val knownMember: Member? = memberRepository.findByEmailAddress(emailAddress)

        if (knownMember == null) {
            val member = Member.register(
                command.memberId,
                emailAddress,
                command.subscriptionStartDate
            )

            mailer.sendWelcomeEmail(member)

            memberRepository.store(member)

            return member
        }

        return null
    }
}
