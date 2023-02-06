package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanathcoding.cleanformvalidation.R
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util.showToast

@Composable
fun RegistrationFormScreen(
    viewModel: RegistrationFormViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                ValidateEvent.Success -> {
                    context.showToast(UiText.StringResource(R.string.registration_successful))
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = viewModel.emailText,
            onValueChange = { email ->
                viewModel.updateEmail(email)
                viewModel.onEvent(RegistrationFormEvent.EmailChanged(email))
            },
            isError = state.emailErrorMsg != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = UiText.StringResource(R.string.email).asString()
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (state.emailErrorMsg != null) {
            Text(
                text = state.emailErrorMsg,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.passwordText,
            onValueChange = { password ->
                viewModel.updatePassword(password)
                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(password))

            },
            isError = state.passwordErrorMsg != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = UiText.StringResource(R.string.password).asString()
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.passwordErrorMsg != null) {
            Text(
                text = state.passwordErrorMsg,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.repeatedPasswordText,
            onValueChange = { repeatPassword ->
                viewModel.updateRepeatedPassword(repeatPassword)
                viewModel.onEvent(RegistrationFormEvent.EmailChanged(repeatPassword))
            },
            isError = state.repeatedPasswordErrorMsg != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = UiText.StringResource(R.string.repeatedPassword).asString()
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.repeatedPasswordErrorMsg != null) {
            Text(
                text = state.repeatedPasswordErrorMsg,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = viewModel.termAcceptCheckBox,
                onCheckedChange = { isChecked ->
                    viewModel.updateAcceptTerms(isChecked)
                    viewModel.onEvent(RegistrationFormEvent.TermAcceptChanged(isChecked))
                }
            )

            Text(
                text = UiText.StringResource(R.string.accept_terms).asString(),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        if (state.termsErrorMsg != null) {
            Text(
                text = state.termsErrorMsg,
                color = MaterialTheme.colors.error
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.onEvent(RegistrationFormEvent.Submit)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = UiText.StringResource(R.string.submit).asString())
        }
    }

}