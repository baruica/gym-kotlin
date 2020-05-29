package gym.membership.use_cases

import common.DomainEvent
import gym.membership.domain.Mailer
import gym.membership.domain.MemberRepository
import java.time.LocalDate

class Send3YearsAnniversaryThankYouEmails(
    private val memberRepository: MemberRepository,
    private val mailer: Mailer
) {
    fun handle(command: Send3YearsAnniversaryThankYouEmailsCommand): List<DomainEvent> {

        val threeYearsAnniversaryMembers = memberRepository.threeYearsAnniversaryMembers(
            LocalDate.parse(command.asOfDate)
        )

        threeYearsAnniversaryMembers.map {
            mailer.sendEmail(
                it.emailAddress,
                "Thank you for your loyalty ${it.emailAddress} !"
            )

            it.mark3YearsAnniversaryThankYouEmailAsSent()
        }

        return threeYearsAnniversaryMembers.map {
            it.raisedEvents.last()
        }
    }
}
