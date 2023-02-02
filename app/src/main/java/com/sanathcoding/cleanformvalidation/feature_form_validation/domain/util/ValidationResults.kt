package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util

import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText

data class ValidationResults(
    val isSuccessful: Boolean,
    val errorMsg: UiText? = null
)
