package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ValidateEmailTest {

    private lateinit var validateEmail: ValidateEmail

    @Before
    fun setUp() {
        validateEmail = ValidateEmail()
    }

    @Test
    fun `If email is blank, return error`() {
        val result = validateEmail.execute("")
        assertEquals(result.isSuccessful, false)
    }

//    @Test
//    fun `If email pattern is not match, return error`() {
//        val result = checkEmailPatternValidation("sggfsds")
//        assertEquals(result, false)
//    }
}