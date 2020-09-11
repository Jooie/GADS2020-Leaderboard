package com.joana.achou.gads2020leaderboard.retrofit


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ServiceBuilder {

    companion object {
        private var INSTANCE: ApiUrls? = null
        private var FORM_INSTANCE: ApiUrls? = null
        private const val BASE_URL = "https://gadsapi.herokuapp.com"
        private const val FORM_URL = "https://docs.google.com/forms/d/e/"



        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        fun getInstance(): ApiUrls {

            if (INSTANCE == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                INSTANCE = retrofit.create(
                    ApiUrls::class.java
                )
            }
            return INSTANCE!!
        }

        fun getFormInstance(): ApiUrls {

            if (FORM_INSTANCE == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(FORM_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                FORM_INSTANCE = retrofit.create(
                    ApiUrls::class.java
                )
            }
            return FORM_INSTANCE!!
        }
    }


}