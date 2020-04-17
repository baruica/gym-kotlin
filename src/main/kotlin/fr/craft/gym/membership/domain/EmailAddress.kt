package fr.craft.gym.membership.domain

import kotlin.text.RegexOption.IGNORE_CASE

data class EmailAddress(val email: String) {

    private val EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"

    init {
        require(Regex(EMAIL_REGEX, IGNORE_CASE).matches(email))
    }

    override fun toString(): String {
        return email
    }
}
