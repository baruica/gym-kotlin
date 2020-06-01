package gym.membership.use_cases

import gym.membership.domain.EmailAddress
import gym.membership.infrastructure.InMemoryMailer
import gym.membership.infrastructure.MemberInMemoryRepository
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RegisterNewMemberTest {

    @Test
    fun handle() {
        val repository = MemberInMemoryRepository()
        val memberId = repository.nextId()
        val emailAddress = "luke@gmail.com"

        assertNull(repository.findByEmailAddress(EmailAddress(emailAddress)))

        val subscriptionId = "subscriptionId def"
        val subscriptionStartDate = "2018-06-05"
        val registerNewMemberCommand = RegisterNewMemberCommand(
            memberId,
            subscriptionId,
            subscriptionStartDate,
            emailAddress
        )

        val mailer = InMemoryMailer()

        val tested = RegisterNewMember(repository, mailer)
        val member = tested.handle(
            registerNewMemberCommand
        )

        if (member != null) {
            assertEquals(memberId, member.id.toString())
            assertEquals(emailAddress, member.emailAddress.value)

            assertTrue(mailer.welcomeEmailWasSentTo(emailAddress))
        }
    }
}
