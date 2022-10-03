package gym.membership.useCases

import gym.membership.domain.*
import gym.subscriptions.domain.SubscriptionId
import java.time.LocalDate

data class RegisterNewMember(
    val memberId: MemberId,
    val subscriptionId: SubscriptionId,
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
