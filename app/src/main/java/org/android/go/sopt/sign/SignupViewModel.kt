package org.android.go.sopt.sign

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.SignServicePool.signService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {

    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult


    fun signUp(id: String, password: String, name : String, skill : String) {
        signService.signup(
            RequestSignUpDto(
                id,
                password,
                name,
                skill
            )
        ).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                }
                else {
                    Log.d("ffffff",response.body().toString())

                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {

            }

        })
    }
}