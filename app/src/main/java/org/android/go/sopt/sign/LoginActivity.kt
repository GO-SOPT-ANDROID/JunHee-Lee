package org.android.go.sopt.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    // LiveData가 저장되어 있는 ViewModel
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getFcmToken()

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
            startActivity(
                Intent(
                    this@LoginActivity,
                    HomeActivity::class.java

                )
            )

        }

        clickSignUp()

    }


    private fun clickSignUp() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener{ task->
            if (!task.isSuccessful) {
                Log.w("tag", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            // Log
            Log.d("tag", "token is $token")
        })
    }
}



