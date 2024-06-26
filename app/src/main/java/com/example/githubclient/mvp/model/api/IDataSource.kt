package com.example.githubclient.mvp.model.api

import com.example.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {
    @GET("users")
    fun getUsers(): Single<List<GitHubUser>>
}
