package fr.craft.gym.membership.domain

import kotlin.text.RegexOption.IGNORE_CASE

data class EmailAddress(val email: String) {

    private val pattern = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$"

    init {
        require(Regex(pattern, IGNORE_CASE).matches(email))
    }

    override fun toString(): String {
        return email
    }
}
