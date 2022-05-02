package com.iram.githubusersearchapp.modules

import com.iram.githubusersearchapp.model.GithubUsers
import com.iram.githubusersearchapp.model.SearchUsers
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUserByName(@Query("q")username:String): Response<SearchUsers>

    @GET("users")
    suspend fun getUserList(): Response<List<GithubUsers>>

}