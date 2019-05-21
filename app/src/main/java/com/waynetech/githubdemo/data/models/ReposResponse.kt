package com.waynetech.githubdemo.data.models

data class ReposResponse(
    val base: Base,
    val body: String,
    val head: Head,
    val html_url: String,
    val number: Int,
    val title: String,
    val user: User
)