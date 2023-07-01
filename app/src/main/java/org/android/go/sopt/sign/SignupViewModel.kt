package org.android.go.sopt.sign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.SoptServicePool.signService

class SignupViewModel : ViewModel() {
    private val _signUpResult: MutableLiveData<Boolean> = MutableLiveData()
    val signUpResult: LiveData<Boolean> = _signUpResult


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

        viewModelScope.launch {
            kotlin.runCatching {
                signService.signup(
                    RequestSignUpDto(
                        id,
                        password,
                        name,
                        skill
                    )
                )
            }.fold(
                {
                    _signUpResult.value = true
                },
                {
                    _signUpResult.value = false
                }
            )
        }
    }


     fun ValidId(id: String?): Boolean {
        return id.isNullOrEmpty() || id.matches(Regex("(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"))
    }

     fun ValidPw(pw: String?): Boolean {
        return pw.isNullOrEmpty() || pw.matches(Regex("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"))
    }




}