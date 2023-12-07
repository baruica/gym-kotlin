package gym.membership.useCases

import Id
import gym.membership.domain.EmailAddress
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class RegisterNewMemberTest : StringSpec({

    "handle" {
        val repository = InMemoryMemberRepository()
        val memberId = repository.nextId()
        val emailAddress = EmailAddress("luke@gmail.com")

        repository.findByEmailAddress(emailAddress).shouldBeNull()

        val mailer = InMemoryMailer()

        val tested = RegisterNewMember.Handler(repository, mailer)
        val member = tested(
            RegisterNewMember(
                Id(memberId),
                Id("subscriptionId def"),
                LocalDate.parse("2018-06-05"),
                EmailAddress(emailAddress.value)
            )
        )

        member?.let {
            it.id shouldBe Id(memberId)
            it.emailAddress shouldBe emailAddress

            mailer.welcomeEmailWasSentTo(emailAddress).shouldBeTrue()
        }
    }
})
