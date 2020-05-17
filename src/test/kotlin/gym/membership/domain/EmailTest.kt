package gym.membership.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class EmailTest {

    @Test
    fun `does not allow invalid emails`() {
        assertFailsWith<IllegalArgumentException> {
            Email("bob[at]gmail.com")
        }
    }
}
