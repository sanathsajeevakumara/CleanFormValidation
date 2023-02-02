package com.sanathcoding.cleanformvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sanathcoding.cleanformvalidation.feature_form_validation.presentation.RegistrationFormScreen
import com.sanathcoding.cleanformvalidation.ui.theme.CleanFormValidationTheme

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