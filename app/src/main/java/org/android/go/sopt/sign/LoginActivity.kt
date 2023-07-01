package org.android.go.sopt.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    // LiveData가 저장되어 있는 ViewModel
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Snackbar.make(
                        binding.root, getString(R.string.signup_complete), Snackbar.LENGTH_SHORT
                    ).show()
                }
            }


        binding.btnLogin.setOnClickListener {
            viewModel.signIn(
                binding.etID.text.toString(),
                binding.etPW.text.toString()
            )
        }

        viewModel.signInResult.observe(this) { signInResult ->
            if (signInResult) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this@LoginActivity,
                        HomeActivity::class.java
                    )
                )
            }
            else{
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }

        }

        clickSignUp()

    }


    private fun clickSignUp() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

}



