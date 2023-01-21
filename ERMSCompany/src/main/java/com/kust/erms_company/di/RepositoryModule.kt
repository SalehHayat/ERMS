package com.kust.erms_company.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kust.erms_company.data.authRepository.AuthRepository
import com.kust.erms_company.data.authRepository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        @Named("company")
        database: FirebaseFirestore
    ) : AuthRepository {
        return AuthRepositoryImpl(auth, database)
    }
}