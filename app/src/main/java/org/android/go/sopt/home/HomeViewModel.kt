package org.android.go.sopt.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.HomeServicePool.homeService
import org.android.go.sopt.model.ResponseHome

class HomeViewModel : ViewModel() {

    private val _followList = MutableLiveData<List<ResponseHome.Data>>()
    val followList: LiveData<List<ResponseHome.Data>> = _followList

    init {
        fetchFollowList()
    }

    private fun fetchFollowList() {
        viewModelScope.launch {
            kotlin.runCatching {
                homeService.listuser()
            }.fold(
                {
                    _followList.value = it.data.toList()
                }, {
                    Log.d("서버통신 실패", it.toString())
                }
            )
        }
    }

}