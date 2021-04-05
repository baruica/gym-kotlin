package gym.membership.useCases

import gym.fifthOfJune
import gym.membership.domain.EmailAddress
import gym.membership.domain.Member
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertTrue

class Send3YearsAnniversaryThankYouEmailsTest {

    @Test
    fun handle() {
        val memberRepository = InMemoryMemberRepository()

        val luke = Member.register(
            UUID.randomUUID().toString(),
            EmailAddress("luke@gmail.com"),
            fifthOfJune().minusYears(3)
        )
        memberRepository.store(luke)

        val mailer = InMemoryMailer()

        val tested = Send3YearsAnniversaryThankYouEmails(memberRepository, mailer)

        tested.handle(
            Send3YearsAnniversaryThankYouEmailsCommand(
                luke.id.toString(),
                780.0
            )
        )

        assertTrue(mailer.threeYearsAnniversaryWasSentTo(luke.emailAddress, 780.0))
    }
}
