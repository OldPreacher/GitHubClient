package com.example.githubclient.mvp.model

class GitHubUsersRepository {
    private val users = listOf(
        GitHubUser("login1"),
        GitHubUser("login2"),
        GitHubUser("login3"),
        GitHubUser("login4"),
        GitHubUser("login5")
    )

    fun getUsers() : List<GitHubUser> {
        return users
    }
}