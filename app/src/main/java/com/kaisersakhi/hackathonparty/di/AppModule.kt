package com.kaisersakhi.hackathonparty.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kaisersakhi.hackathonparty.R
import com.kaisersakhi.hackathonparty.data.api.IYelpAPI
import com.kaisersakhi.hackathonparty.data.deserilizers.BusinessOutletDeserializer
import com.kaisersakhi.hackathonparty.data.models.YelpSearchResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideIYelpAPIService(@ApplicationContext context : Context): IYelpAPI {
        val gsonConverter: Gson = GsonBuilder()
            .registerTypeAdapter(YelpSearchResponse::class.java, BusinessOutletDeserializer())
            .create()

        val httpClient = OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.header("Authorization", "Bearer ${context.resources.getString(R.string.api_key)}")
                it.proceed(requestBuilder.build())
            }
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .client(httpClient)
            .build()

        return retrofit.create(IYelpAPI::class.java)

    }
}