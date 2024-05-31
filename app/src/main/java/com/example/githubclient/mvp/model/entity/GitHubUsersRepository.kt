package com.example.githubclient.mvp.model.entity

import io.reactivex.rxjava3.core.Single

class GitHubUsersRepository {
    private val users = listOf(
        GitHubUser("", "login1"),
        GitHubUser("", "login2"),
        GitHubUser("", "login3"),
        GitHubUser("", "login4"),
        GitHubUser("", "login5")
    )

    fun getUsers(): Single<List<GitHubUser>> {
        return Single.just(users)
    }
}