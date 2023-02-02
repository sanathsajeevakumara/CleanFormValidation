package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case

import com.sanathcoding.cleanformvalidation.R
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.ValidationResults
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.checkEmailPatternValidation

class ValidateEmail {

    fun execute(email: String): ValidationResults {

        if (email.isBlank()) {
            return ValidationResults(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.email_blank_error,
                )
            )
        }
        if (!checkEmailPatternValidation(email)) {
            return ValidationResults(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.email_pattern_error
                )
            )
        }
        return ValidationResults(
            isSuccessful = true
        )
    }

}