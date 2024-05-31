package com.example.githubclient.mvp.model.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ApiHolder {
    val api: IDataSource by lazy {
        val logging = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .build()

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }
}
