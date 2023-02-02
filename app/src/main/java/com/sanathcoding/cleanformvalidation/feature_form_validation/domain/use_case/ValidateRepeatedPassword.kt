package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case

import com.sanathcoding.cleanformvalidation.R
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.ValidationResults

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResults {

        if (repeatedPassword != password) {
            return ValidationResults(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    R.string.password_dont_match_error,
                )
            )
        }
        return ValidationResults(
            isSuccessful = true
        )
    }
}