package org.android.go.sopt.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import org.android.go.sopt.RequestLogin
import org.android.go.sopt.ResponseLogin
import org.android.go.sopt.SignServicePool
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.home.HomeActivity
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val signService = SignServicePool.signService


    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        clickSignUp()

    }

    private fun setResultSignUp() {
        val intent = Intent(this, HomeActivity::class.java)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    Snackbar.make(
                        binding.root, getString(R.string.signup_complete), Snackbar.LENGTH_SHORT
                    ).show()
                }
            }


        binding.btnLogin.setOnClickListener {
            signService.signin(with(binding) {
                RequestLogin(
                    etID.text.toString(),
                    etPW.text.toString(),
                )
            }).enqueue(object : retrofit2.Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>,
                ) {

                    if (response.isSuccessful) {
                        response.body()?.message?.let {
                            Toast.makeText(
                                this@LoginActivity, it, Toast.LENGTH_SHORT
                            ).show()
                        } ?: getString(R.string.login_complete)
                        startActivity(intent)

                    } else {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.wrong_input),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    // 왜 안 오노
                    t.message?.let {
                        Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                    } ?: "서버통신 실패(응답값 X)"

                }
            })

        }

    }

    private fun clickSignUp() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}



