package com.example.teamproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject1.databinding.ActivityMyPageBinding

//개인정보
class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}