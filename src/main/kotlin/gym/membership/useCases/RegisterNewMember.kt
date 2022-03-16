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

            val knownMember: Member? = memberRepository.findByEmailAddress(command.email)

            if (knownMember == null) {
                val member = Member.register(
                    command.memberId,
                    command.email,
                    command.subscriptionStartDate
                )

                mailer.sendWelcomeEmail(member)

                memberRepository.store(member)

                return member
            }

            return null
        }
    }
}
