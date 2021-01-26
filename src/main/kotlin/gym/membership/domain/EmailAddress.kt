package gym.membership.domain

import kotlin.text.RegexOption.IGNORE_CASE

data class EmailAddress(val value: String) {
    init {
        require(Regex("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$", IGNORE_CASE).matches(value)) {
            "EmailAddress must be a valid email address, was [$value]"
        }
    }
}
