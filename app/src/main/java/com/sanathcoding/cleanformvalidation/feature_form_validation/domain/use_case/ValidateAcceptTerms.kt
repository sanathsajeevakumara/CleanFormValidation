package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case

import com.sanathcoding.cleanformvalidation.R
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.ConstValue.Companion.PASSWORD_LENGTH
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.ValidationResults

class ValidateAcceptTerms {

    fun execute(isTermAccept: Boolean): ValidationResults {

        if (isTermAccept) {
            return ValidationResults(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    R.string.term_accept_error,
                )
            )
        }
        return ValidationResults(
            isSuccessful = true
        )
    }
}