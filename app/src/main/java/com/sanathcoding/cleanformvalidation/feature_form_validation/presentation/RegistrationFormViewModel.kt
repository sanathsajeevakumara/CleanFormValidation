package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

import android.app.Application
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
    private val validateAcceptTerms: ValidateAcceptTerms,
    private val application: Application
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())
        private set

    var emailText by mutableStateOf("")
        private set
    var passwordText by mutableStateOf("")
        private set
    var repeatedPasswordText by mutableStateOf("")
        private set
    var termAcceptCheckBox by mutableStateOf(false)
        private set

    private val validationEventChannel = Channel<ValidateEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun updateEmail(email: String) {
        emailText = email
    }

    fun updatePassword(password: String) {
        passwordText = password
    }

    fun updateRepeatedPassword(repeatPassword: String) {
        repeatedPasswordText = repeatPassword
    }

    fun updateAcceptTerms(isTermAccept: Boolean) {
        termAcceptCheckBox = isTermAccept
    }

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is RegistrationFormEvent.TermAcceptChanged -> {
                state = state.copy(isAcceptTerms = event.isAcceptTerm)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(emailText)
        val passwordResult = validatePassword.execute(passwordText)
        val repeatedPasswordResult =
            validateRepeatedPassword.execute(passwordText, repeatedPasswordText)
        val termAcceptResult = validateAcceptTerms.execute(termAcceptCheckBox)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termAcceptResult
        ).any { !it.isSuccessful }


        state = state.copy(
            emailErrorMsg = emailResult.errorMsg?.asString(application.applicationContext),
            passwordErrorMsg = passwordResult.errorMsg?.asString(application.applicationContext),
            repeatedPasswordErrorMsg =
            repeatedPasswordResult.errorMsg?.asString(application.applicationContext),
            termsErrorMsg = termAcceptResult.errorMsg?.asString(application.applicationContext),
        )
        if (hasError) return

        viewModelScope.launch {
            validationEventChannel.send(ValidateEvent.Success)
        }
    }

}