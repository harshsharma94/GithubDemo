package com.waynetech.githubdemo.data

import com.waynetech.githubdemo.data.models.ReposResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PullsService {

    @GET("/repos/{ownerName}/{repoName}/pulls")
    fun getForecastWeather(
        @Path("ownerName") ownerName: String, @Path("repoName") repoName: String
    ): Observable<List<ReposResponse>>

}