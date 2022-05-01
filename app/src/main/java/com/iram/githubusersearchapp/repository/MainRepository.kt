package com.iram.githubusersearchapp.repository

import com.iram.githubusersearchapp.model.GithubUsers
import com.iram.githubusersearchapp.model.SearchUsers
import com.iram.githubusersearchapp.modules.ApiService
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUserList(): Response<List<GithubUsers>> {
        return apiService.getUserList()
    }
    suspend fun getUserByName(userName:String): Response<SearchUsers> {
        return apiService.getUserByName(userName)
    }
    suspend fun getUserInfo(userName:String): Response<GithubUsers> {
        return apiService.getUserInfo(userName)
    }
}