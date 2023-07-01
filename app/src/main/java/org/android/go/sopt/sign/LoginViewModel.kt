package org.android.go.sopt.sign

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.RequestLogin
import org.android.go.sopt.ResponseLogin
import org.android.go.sopt.SoptServicePool.signService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _signInResult: MutableLiveData<Boolean> = MutableLiveData()
    val signInResult: LiveData<Boolean> = _signInResult



    fun signIn(id: String, password: String) {

        viewModelScope.launch {
            kotlin.runCatching {
                signService.signin(
                    RequestLogin(
                        id,
                        password
                    )
                )
            }.fold(
                {
                    _signInResult.value = true
                },{
                    _signInResult.value = false
                }
            )
        }
    }
}