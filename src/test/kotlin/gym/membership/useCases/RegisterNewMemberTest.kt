package gym.membership.useCases

import gym.membership.domain.EmailAddress
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RegisterNewMemberTest {

    @Test
    fun handle() {
        val repository = InMemoryMemberRepository()
        val memberId = repository.nextId()
        val emailAddress = "luke@gmail.com"

        assertNull(repository.findByEmailAddress(EmailAddress(emailAddress)))

        val mailer = InMemoryMailer()

        val tested = RegisterNewMember(repository, mailer)
        val member = tested.handle(
            RegisterNewMemberCommand(
                memberId,
                "subscriptionId def",
                "2018-06-05",
                emailAddress
            )
        )

        member?.let {
            assertEquals(memberId, it.id.toString())
            assertEquals(emailAddress, it.emailAddress.value)

            assertTrue(mailer.welcomeEmailWasSentTo(emailAddress))
        }
    }
}
