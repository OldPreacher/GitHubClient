package com.example.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("login") val login: String,
    @Expose @SerializedName("avatar_url") val avatarUrl: String? = null,
    @Expose @SerializedName("repos_url") val repositoryUrl: String? = null
) : Parcelable
