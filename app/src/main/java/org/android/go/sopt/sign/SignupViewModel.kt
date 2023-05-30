package org.android.go.sopt.sign

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val hobby = MutableLiveData<String>()

    private val _checksignup : MediatorLiveData<Boolean> = MediatorLiveData()
    val checksignup : LiveData<Boolean> = _checksignup

    init {
        setupFormValidation()
    }

    private fun setupFormValidation() {
        _checksignup.addSource(id) { validateForm() }
        _checksignup.addSource(pw) { validateForm() }
        _checksignup.addSource(name) { validateForm() }
        _checksignup.addSource(hobby) { validateForm() }
    }

    private fun validateForm() {
        val isFormValid = canUserSignUp()
        _checksignup.value = isFormValid
    }
    
    private fun canUserSignUp(): Boolean {
        return ValidId(id.value) && ValidPw(pw.value) && id.value?.isNotBlank() == true &&
                pw.value?.isNotBlank() == true && name.value?.isNotBlank() == true && hobby.value?.isNotBlank() == true
    }

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


     fun ValidId(id: String?): Boolean {
        return id.isNullOrEmpty() || id.matches(Regex("(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"))
    }

     fun ValidPw(pw: String?): Boolean {
        return pw.isNullOrEmpty() || pw.matches(Regex("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"))
    }




}