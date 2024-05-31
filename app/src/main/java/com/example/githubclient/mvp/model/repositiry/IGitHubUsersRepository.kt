package com.example.githubclient.mvp.model.repositiry

import com.example.githubclient.mvp.model.entity.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepository {
    fun getUsers(): Single<List<GitHubUser>>
}