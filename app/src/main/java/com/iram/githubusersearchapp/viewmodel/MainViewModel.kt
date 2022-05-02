package com.iram.githubusersearchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iram.githubusersearchapp.model.GithubUsers
import com.iram.githubusersearchapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        onError(error)
    }
    val userListLiveData = MutableLiveData<List<GithubUsers>>()
    val searchUserListLiveData = MutableLiveData<List<GithubUsers>>()
    val usersLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    private val error = "Unable to reach server !!";

    init {
        getUserList()
    }

    private fun getUserList() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getUserList()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        userListLiveData.postValue(response.body())
                        usersLoadError.postValue("")
                        loading.postValue(false)
                    } else {
                        onError(error)
                    }
                } catch (e: Exception) {
                    onError(error)
                }
            }
        }
    }

    fun getUserListByName(query: String) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getUserByName(query)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        searchUserListLiveData.postValue(response.body()?.items)
                        usersLoadError.postValue("")
                        loading.postValue(false)
                    } else {
                        onError(error)
                    }
                } catch (e: Exception) {
                    onError(error)
                }
            }
        }
    }

    private fun onError(message: String) {
        usersLoadError.postValue(message)
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}