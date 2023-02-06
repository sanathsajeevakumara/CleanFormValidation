package com.sanathcoding.cleanformvalidation.feature_form_validation.domain.util

import android.content.Context
import android.widget.Toast
import com.sanathcoding.cleanformvalidation.feature_form_validation.core.util.UiText

fun Context.showToast(message: UiText) {
    Toast.makeText(this, message.asString(this), Toast.LENGTH_LONG).show()
}