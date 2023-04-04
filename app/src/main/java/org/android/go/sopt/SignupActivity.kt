package org.android.go.sopt


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit  var binding : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickSignUp_end()
    }

    private fun clickSignUp_end(){

        binding.btnSignupEnd.setOnClickListener {
            if(binding.etName.length() > 0 &&binding.etID.length()in 6..10 && binding.etPW.length()in 8..12 && binding.etHobby.length() > 0 ){
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", binding.etID.text.toString())
                intent.putExtra("password", binding.etPW.text.toString())
                setResult(RESULT_OK, intent)
                finish()


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