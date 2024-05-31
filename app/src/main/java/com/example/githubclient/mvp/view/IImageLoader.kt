package com.example.githubclient.mvp.view

interface IImageLoader<T> {
    fun loadInfo(url: String, container: T)
}