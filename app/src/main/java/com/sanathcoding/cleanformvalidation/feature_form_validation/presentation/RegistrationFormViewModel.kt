package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegistrationFormViewModel: ViewModel() {

    var state by mutableStateOf(RegistrationFormState())
        private set



}