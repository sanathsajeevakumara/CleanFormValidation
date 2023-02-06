package com.sanathcoding.cleanformvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanathcoding.cleanformvalidation.feature_form_validation.presentation.RegistrationFormScreen
import com.sanathcoding.cleanformvalidation.feature_form_validation.presentation.RegistrationFormViewModel
import com.sanathcoding.cleanformvalidation.ui.theme.CleanFormValidationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanFormValidationTheme {
                RegistrationFormScreen()
            }
        }
    }
}