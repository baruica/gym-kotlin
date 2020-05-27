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

        threeYearsAnniversaryMembers.mapValues {
            mailer.sendEmail(
                it.value.emailAddress,
                "Thank you for your loyalty ${it.value.emailAddress} !"
            )

            it.value.mark3YearsAnniversaryThankYouEmailAsSent()
        }

        return threeYearsAnniversaryMembers.values.map {
            it.raisedEvents.last()
        }
    }
}
