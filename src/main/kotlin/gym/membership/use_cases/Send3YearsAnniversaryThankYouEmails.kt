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
            mailer.send3YearsAnniversaryEmail(it)
        }

        return threeYearsAnniversaryMembers.map {
            it.raisedEvents.last()
        }
    }
}
