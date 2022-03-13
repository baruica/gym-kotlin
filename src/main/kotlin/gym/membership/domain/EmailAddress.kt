package gym.membership.domain

import kotlin.text.RegexOption.IGNORE_CASE

@JvmInline
value class EmailAddress(val value: String) {

    init {
        require(isValidEmail(value)) {
            "EmailAddress must be a valid email address, was [$value]"
        }
    }

    private fun isValidEmail(value: String) =
        Regex("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$", IGNORE_CASE).matches(value)
}
