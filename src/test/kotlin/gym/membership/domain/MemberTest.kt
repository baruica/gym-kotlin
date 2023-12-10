package gym.membership.domain

import Id
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import java.time.LocalDate

class MemberTest : StringSpec({

    "is 3 years anniversary" {
        val memberWith3yearsAnniversaryOnTheFifthOfJune = Member.register(
            Id("member abc"),
            EmailAddress("julie@gmail.com"),
            LocalDate.parse("2018-06-05").minusYears(3)
        )

        memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-04"))
            .shouldBeFalse()
        memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-06-05"))
            .shouldBeTrue()
        memberWith3yearsAnniversaryOnTheFifthOfJune.isThreeYearsAnniversary(LocalDate.parse("2018-07-06"))
            .shouldBeFalse()
    }
})
