package com.sanathcoding.cleanformvalidation.feature_form_validation.presentation

sealed interface ValidateEvent {
    object Success: ValidateEvent
}