package gym.membership.useCases

import Id
import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import java.time.LocalDate

data class RegisterNewMember(
    val memberId: Id<String>,
    val subscriptionId: Id<String>,
    val subscriptionStartDate: LocalDate,
    val email: EmailAddress,
) {
    class Handler(
        private val memberRepository: MemberRepository,
        private val mailer: Mailer,
    ) {
        operator fun invoke(command: RegisterNewMember): Member? {

            memberRepository.findByEmailAddress(command.email)
                ?: return Member.register(
                    command.memberId,
                    command.email,
                    command.subscriptionStartDate
                )
                    .also { mailer.sendWelcomeEmail(it) }
                    .also { memberRepository.store(it) }

            return null
        }
    }
}
