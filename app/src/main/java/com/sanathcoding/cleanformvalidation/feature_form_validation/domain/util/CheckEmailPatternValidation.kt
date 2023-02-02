package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util

import android.util.Patterns

fun checkEmailPatternValidation(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}