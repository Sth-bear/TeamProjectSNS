package com.example.teamproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject1.databinding.ActivityDetailBinding

//타인 디테일 페이지
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}