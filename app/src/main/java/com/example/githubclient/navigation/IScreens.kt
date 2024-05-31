package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.entity.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetails(user: GitHubUser): Screen
}