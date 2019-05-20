package com.waynetech.githubdemo.data

import com.waynetech.githubdemo.data.models.ReposResponse
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val pullsService: PullsService) : Repository {
    override fun getPulls(ownerName: String, repoName: String): Observable<List<ReposResponse>> {
        return pullsService.getForecastWeather(ownerName, repoName)
    }
}