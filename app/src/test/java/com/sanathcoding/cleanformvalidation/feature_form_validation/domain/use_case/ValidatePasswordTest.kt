package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidatePasswordTest {

    private lateinit var validatePassword: ValidatePassword

    @Before
    fun setUp() {
        validatePassword = ValidatePassword()
    }

    @Test
    fun `Password length is less than 8, returns error`() {
        val result = validatePassword.execute("1qaz2ws")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `Password is letter only, returns error`() {
        val result = validatePassword.execute("qazwsxed")
        assertEquals(result.isSuccessful, false)
    }

    @Test
    fun `Password is digit only, returns error`() {
        val result = validatePassword.execute("12345678")
        assertEquals(result.isSuccessful, false)
    }
}