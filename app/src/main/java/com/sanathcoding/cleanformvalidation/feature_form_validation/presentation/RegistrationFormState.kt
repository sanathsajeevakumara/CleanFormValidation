package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

data class RegistrationFormState(
    val email: String = "",
    val emailErrorMsg: String? = null,
    val password: String = "",
    val passwordErrorMsg: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordErrorMsg: String? = null,
    val isAcceptTerms: Boolean = false,
    val termsErrorMsg: String? = null
)
