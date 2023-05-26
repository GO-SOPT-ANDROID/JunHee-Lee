package org.android.go.sopt.sign


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.SignServicePool
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }

    // LiveData가 저장되어 있는 ViewModel
    private val viewModel: SignupViewModel by viewModels<SignupViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.etID.isEnabled = false
        binding.etPW.isEnabled = false
        binding.etHobby.isEnabled = false
        binding.btnSignupEnd.isEnabled = false

        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
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
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etPW.isEnabled = binding.etID.text.length in 6..10
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })

        binding.etPW.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etHobby.isEnabled = binding.etPW.text.length in 8..12
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })

        binding.etHobby.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSignupEnd.isEnabled = binding.etHobby.text.isNotBlank()
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnSignupEnd.isEnabled = canUserSignIn()
            }

        })


        binding.btnSignupEnd.setOnClickListener {
            viewModel.signUp(
                binding.etID.text.toString(),
                binding.etPW.text.toString(),
                binding.etName.text.toString(),
                binding.etHobby.text.toString()
            )
        }

        viewModel.signUpResult.observe(this) { signupResult ->
            startActivity(
                Intent(
                    this@SignupActivity,
                    LoginActivity::class.java
                )
            )
        }


    }


    private fun canUserSignIn(): Boolean {
        return binding.etID.text.length in 6..10 && binding.etPW.text.length in 8..12 && binding.etName.text.isNotBlank() && binding.etHobby.text.isNotBlank()
    }


}
