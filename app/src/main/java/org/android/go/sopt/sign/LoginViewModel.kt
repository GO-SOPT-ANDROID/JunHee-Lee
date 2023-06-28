package org.android.go.sopt.sign

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.RequestLogin
import org.android.go.sopt.ResponseLogin
import org.android.go.sopt.SignServicePool.signService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _signInResult: MutableLiveData<ResponseLogin> = MutableLiveData()
    val signInResult: LiveData<ResponseLogin> = _signInResult


    fun signIn(id: String, password: String) {
        signService.signin(
            RequestLogin(
                id,
                password
            )
        ).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                if (response.isSuccessful) {
                    _signInResult.value = response.body()
                }
                else {
                        Log.d("ffffff",response.body().toString())

                    }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {

            }

        })
    }
}