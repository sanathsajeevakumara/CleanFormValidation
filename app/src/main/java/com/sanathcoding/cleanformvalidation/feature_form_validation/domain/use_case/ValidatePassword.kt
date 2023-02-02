package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case

import com.sanathcoding.cleanformvalidation.R
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.ConstValue.Companion.PASSWORD_LENGTH
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.ValidationResults
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.checkEmailPatternValidation

class ValidatePassword {

    fun execute(password: String): ValidationResults {

        val containsLetterAndDigits = password.any { it.isLetter() } && password.any {it.isDigit()}

        if (password.length < PASSWORD_LENGTH) {
            return ValidationResults(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    R.string.password_blank_error,
                    PASSWORD_LENGTH
                )
            )
        }
        if (!containsLetterAndDigits) {
                return ValidationResults(
                    isSuccessful = false,
                    errorMsg = UiText.StringResource(
                        resId = R.string.password_dont_contain_letter_or_digit_error
                    )
                )
            }
        return ValidationResults(
            isSuccessful = true
        )
    }
}