package gym.membership.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class EmailAddressTest : StringSpec({

    "does not allow an invalid email address" {
        shouldThrow<IllegalArgumentException> {
            EmailAddress("bob[at]gmail.com")
        }
    }
})
