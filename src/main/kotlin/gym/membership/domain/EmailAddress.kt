package gym.membership.domain

import kotlin.text.RegexOption.IGNORE_CASE

data class EmailAddress(val value: String) {

    private val pattern = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$"

    init {
        require(Regex(pattern, IGNORE_CASE).matches(value))
    }

    override fun toString(): String {
        return value
    }
}
