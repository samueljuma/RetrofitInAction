package com.samueljuma.retrofitinaction.di

import com.samueljuma.retrofitinaction.network.CommentsAPIService
import com.samueljuma.retrofitinaction.network.RetrofitClient
import com.samueljuma.retrofitinaction.repository.CommentsRepository
import com.samueljuma.retrofitinaction.repository.CommentsRepositoryImpl
import com.samueljuma.retrofitinaction.viewmodel.CommentsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    // Retrofit
    single { RetrofitClient.create() }
    // API Service
    single { get<Retrofit>().create(CommentsAPIService::class.java) }
    // Dispatcher
    single { Dispatchers.IO }
    // Repository
    single<CommentsRepository> { CommentsRepositoryImpl(get(), get()) }
    // ViewModel
    single { CommentsViewModel(get()) }

}