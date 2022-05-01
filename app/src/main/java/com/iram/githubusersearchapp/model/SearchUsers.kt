package com.iram.githubusersearchapp.model

import com.google.gson.annotations.SerializedName

data class SearchUsers(
    @SerializedName("total_count")
    val totalCount: Integer,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var items: List<GithubUsers>
)
