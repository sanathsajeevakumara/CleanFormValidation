package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

sealed interface RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent
    data class PasswordChanged(val password: String) : RegistrationFormEvent
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent
    data class TermAcceptChanged(val isAcceptTerm: Boolean) : RegistrationFormEvent
    object Submit : RegistrationFormEvent
}
