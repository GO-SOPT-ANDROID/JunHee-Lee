package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>


    lateinit  var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResultSignUp()
        clickSignUp()

    }

    private fun setResultSignUp(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                val id = result.data?.getStringExtra("id") ?: ""
                val password = result.data?.getStringExtra("password")?:""

                Snackbar.make(
                    binding.root,
                    "회원가입이 완료되었습니다.",
                    Snackbar.LENGTH_SHORT
                ).show()

            binding.btnLogin.setOnClickListener {
                if (binding.etID.text.toString() == id && binding.etPW.text.toString() == password) {
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Snackbar.make(
                        binding.root,
                        "정보 입력이 잘못되었습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                }
            }
        }
    }

    private fun clickSignUp(){
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}