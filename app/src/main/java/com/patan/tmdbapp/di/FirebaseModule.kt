package com.patan.tmdbapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.patan.tmdbapp.network.FirebaseClient
import com.patan.tmdbapp.network.FirebaseClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseUser {
        return FirebaseAuth.getInstance().currentUser!!
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseClient(
        firestore: FirebaseFirestore
    ): FirebaseClient {
        return FirebaseClientImpl(firestore)
    }
}
