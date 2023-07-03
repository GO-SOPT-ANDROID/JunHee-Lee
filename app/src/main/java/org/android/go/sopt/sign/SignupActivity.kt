package org.android.go.sopt.sign


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding
import timber.log.Timber

class SignupActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignupBinding.inflate(layoutInflater) }

    // LiveData가 저장되어 있는 ViewModel
    private val viewModel: SignupViewModel by viewModels()


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

        viewModel.checksignup.observe(this) { isFormValid ->
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

            if (signupResult){
                val intent = Intent(
                    this@SignupActivity,
                    LoginActivity::class.java
                )
                setResult(RESULT_OK, intent)
                finish()
            }
            else{
                Snackbar.make(
                    binding.root, "회원가입 실패", Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }


}
