package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    /*fun usersDetails(user: GitHubUser): Screen*/
}