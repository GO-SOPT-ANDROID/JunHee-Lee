package org.android.go.sopt


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private val signUpService = ServicePool.signUpService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            if (canUserSignIn()) {
                signUpService.login(
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
                            response.body()?.message?.let { Toast.makeText( this@SignupActivity ,it, Toast.LENGTH_SHORT).show()} ?: "회원가입에 성공했습니다."

                            if (!isFinishing) finish()
                        } else {
                            // 실패한 응답
                            response.body()?.message?.let { Toast.makeText( this@SignupActivity ,it, Toast.LENGTH_SHORT).show() } ?: "서버통신 실패(40X)"
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                        // 왜 안 오노
                        t.message?.let { Toast.makeText( this@SignupActivity ,it, Toast.LENGTH_SHORT).show() } ?: "서버통신 실패(응답값 X)"
                    }
                })

            }

//                val intent = Intent(this, LoginActivity::class.java)
//                intent.putExtra("id", binding.etID.text.toString())
//                intent.putExtra("password", binding.etPW.text.toString())
//                intent.putExtra("name", binding.etName.text.toString())
//                intent.putExtra("hobby", binding.etHobby.text.toString())
//                setResult(RESULT_OK, intent)
//                finish()

            else {
                Snackbar.make(
                    binding.root,
                    "정보 입력이 잘못되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()

            }
        }

    }
}
