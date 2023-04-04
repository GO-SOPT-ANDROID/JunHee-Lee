package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    lateinit  var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val hobby = intent.getStringExtra("hobby")
        binding.tvName.text= "이름 : $name"
        binding.tvIntroduce.text= "특기 : $hobby"
    }
}