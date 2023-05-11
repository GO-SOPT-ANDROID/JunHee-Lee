package org.android.go.sopt.sign


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.SignServicePool
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private val signService = SignServicePool.signService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etID.isEnabled = false
        binding.etPW.isEnabled = false
        binding.etHobby.isEnabled = false
        binding.btnSignupEnd.isEnabled = false

        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etID.isEnabled = binding.etName.text.isNotBlank()

                if (binding.etName.text.isNotBlank()) {
                    binding.btnSignupEnd.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })

        binding.etID.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etPW.isEnabled = binding.etID.text.length in 6..10
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })

        binding.etPW.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etHobby.isEnabled = binding.etPW.text.length in 8..12
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })

        binding.etHobby.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSignupEnd.isEnabled = binding.etHobby.text.isNotBlank()
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })
        clickSignUp_end()
    }


    private fun canUserSignIn(): Boolean {
        return binding.etID.text.length in 6..10
                && binding.etPW.text.length in 8..12
                && binding.etName.text.isNotBlank()
                && binding.etHobby.text.isNotBlank()
    }

    private fun clickSignUp_end() {

        binding.btnSignupEnd.setOnClickListener {

            signService.signup(
                with(binding) {
                    RequestSignUpDto(
                        etID.text.toString(),
                        etPW.text.toString(),
                        etName.text.toString(),
                        etHobby.text.toString()
                    )
                }
            ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                override fun onResponse(
                    call: Call<ResponseSignUpDto>,
                    response: Response<ResponseSignUpDto>,
                ) {
                    if (response.isSuccessful) {
                        response.body()?.message?.let {
                            Toast.makeText(
                                this@SignupActivity,
                                it,
                                Toast.LENGTH_SHORT
                            ).show()
                        } ?: getString(R.string.signup_complete)

                        if (!isFinishing) finish()
                    } else {
                        // 실패한 응답
                        response.body()?.message?.let {
                            Toast.makeText(
                                this@SignupActivity,
                                it,
                                Toast.LENGTH_SHORT
                            ).show()
                        } ?: "서버통신 실패(40X)"
                    }
                }

                override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                    // 왜 안 오노
                    t.message?.let {
                        Toast.makeText(this@SignupActivity, it, Toast.LENGTH_SHORT).show()
                    } ?: "서버통신 실패(응답값 X)"
                }
            })
        }
    }

}
