package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateAcceptTerms
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateEmail
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidatePassword
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateRepeatedPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationFormViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val validateAcceptTerms: ValidateAcceptTerms
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())
        private set

    private val validationEventChannel = Channel<ValidateEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(
                    repeatedPassword = event.repeatedPassword
                )
            }
            is RegistrationFormEvent.TermAcceptChanged -> {
                state = state.copy(
                    isAcceptTerms = event.isAcceptTerm
                )
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult =
            validateRepeatedPassword.execute(state.password, state.repeatedPassword)
        val termAcceptResult = validateAcceptTerms.execute(state.isAcceptTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termAcceptResult
        ).any { it.errorMsg != null }

        if (hasError) {
            state =state.copy(
                emailErrorMsg = state.emailErrorMsg,
                passwordErrorMsg = state.passwordErrorMsg,
                repeatedPasswordErrorMsg = state.repeatedPasswordErrorMsg,
                termsErrorMsg = state.termsErrorMsg
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidateEvent.Success)
        }

    }

}