package com.jetsada.unittest

import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.jetsada.unittest.unit.RegistrationUtil

class RegistrationUtilTest {

    @Test
    fun `empty username return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
        "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password return true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Jetsada",
            "123",
            "123"
        )
        assertThat(result).isTrue()

    }

    @Test
    fun `username already exists return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Jetsada",
            "123456",
            "abcdefg"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Jetsada",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Jetsada",
            "abcdefg5",
            "abcdefg5"
        )
        assertThat(result).isFalse()
    }

}