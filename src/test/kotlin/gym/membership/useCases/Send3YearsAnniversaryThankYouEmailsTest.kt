package gym.membership.useCases

import com.github.guepardoapps.kulid.ULID
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class Send3YearsAnniversaryThankYouEmailsTest : AnnotationSpec() {

    @Test
    fun handle() {
        val memberRepository = InMemoryMemberRepository()

        val luke = Member.register(
            ULID.random(),
            EmailAddress("luke@gmail.com"),
            LocalDate.parse("2018-06-05").minusYears(3)
        )
        memberRepository.store(luke)

        val mailer = InMemoryMailer()

        val tested = Send3YearsAnniversaryThankYouEmailsHandler(memberRepository, mailer)

        tested(
            Send3YearsAnniversaryThankYouEmails(
                luke.id.toString(),
                780.0
            )
        )

        mailer.threeYearsAnniversaryWasSentTo(luke.emailAddress, 780.0).shouldBeTrue()
    }
}
