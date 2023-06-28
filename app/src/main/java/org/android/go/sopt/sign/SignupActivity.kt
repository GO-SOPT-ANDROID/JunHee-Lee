package org.android.go.sopt.sign


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
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

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        viewModel.id.observe(this) {
            if (viewModel.ValidId(it)) {
                binding.tvIdWarning.visibility = View.INVISIBLE
            } else
                binding.tvIdWarning.visibility = View.VISIBLE
        }

        viewModel.pw.observe(this) {
            if (viewModel.ValidPw(it)) {
                binding.tvPwWarning.visibility = View.INVISIBLE
            } else
                binding.tvPwWarning.visibility = View.VISIBLE
        }

        viewModel.checksignup.observe(this){ isFormValid ->
            binding.btnSignupEnd.isEnabled = isFormValid
        }


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





}
