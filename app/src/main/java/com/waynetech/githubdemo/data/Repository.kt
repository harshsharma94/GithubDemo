package com.waynetech.githubdemo.data

import com.waynetech.githubdemo.data.models.ReposResponse
import io.reactivex.Observable

interface Repository {

    fun getPulls(ownerName: String, repoName: String): Observable<List<ReposResponse>>

}