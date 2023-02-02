package com.sanathcoding.cleanformvalidation.feature_form_validation.di

import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateAcceptTerms
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateEmail
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidatePassword
import com.sanathcoding.cleanformvalidation.feature_form_validation.domain.use_case.ValidateRepeatedPassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FormModule {

    @Singleton
    @Provides
    fun provideValidateEmail(): ValidateEmail {
        return ValidateEmail()
    }

    @Singleton
    @Provides
    fun provideValidatePassword(): ValidatePassword {
        return ValidatePassword()
    }

    @Singleton
    @Provides
    fun provideValidateRepeatedPassword(): ValidateRepeatedPassword {
        return ValidateRepeatedPassword()
    }

    @Singleton
    @Provides
    fun provideValidateAcceptTerms(): ValidateAcceptTerms {
        return ValidateAcceptTerms()
    }

}