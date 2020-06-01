package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.domain.Mailer
import gym.membership.domain.Member
import gym.membership.domain.MemberRepository
import gym.subscriptions.domain.SubscriptionId
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
                SubscriptionId(command.subscriptionId),
                LocalDate.parse(command.subscriptionStartDate)
            )

            mailer.sendWelcomeEmail(member)

            memberRepository.store(member)

            return member
        }

        return null
    }
}
