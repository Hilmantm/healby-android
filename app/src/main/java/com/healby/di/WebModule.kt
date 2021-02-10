package com.healby.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.healby.BuildConfig
import com.healby.service.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object WebModule {

    @Provides
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

//    @Provides
//    fun providesFirebaseAnalytics(
//        @ApplicationContext ctx: Context
//    ): FirebaseAnalytics {
//        return FirebaseAnalytics.getInstance(ctx)
//    }

    @Provides
    fun providesChuckerCollector(
        @ApplicationContext ctx: Context
    ): ChuckerCollector {
        return ChuckerCollector(
            context = ctx,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_DAY
        )
    }

    @Provides
    fun providesChuckerInterceptor(
        @ApplicationContext ctx: Context,
        collector: ChuckerCollector
    ): ChuckerInterceptor {
        return ChuckerInterceptor(
            context = ctx,
            collector = collector,
            maxContentLength = 250000L,
            alwaysReadResponseBody = true
        )
    }

    @Provides
    fun providesHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRxJavaCallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    fun providesNetworkService(
        baseUrl: String,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

}