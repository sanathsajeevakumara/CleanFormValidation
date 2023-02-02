package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateAcceptTerms
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateEmail
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidatePassword
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateRepeatedPassword
import dagger.hilt.android.lifecycle.HiltViewModel
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



}