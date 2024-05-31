package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.GitHubUser
import com.example.githubclient.ui.fragment.UserDetailsFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userDetails(user: GitHubUser) = FragmentScreen { UserDetailsFragment.newInstance(user) }
}