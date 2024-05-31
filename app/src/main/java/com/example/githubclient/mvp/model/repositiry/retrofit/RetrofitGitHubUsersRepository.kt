package com.example.githubclient.mvp.model.repositiry.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.repositiry.IGitHubUsersRepository
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUsersRepository(private val api: IDataSource) : IGitHubUsersRepository {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}