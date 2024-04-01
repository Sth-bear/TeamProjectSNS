package com.example.teamproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject1.databinding.ActivityLobbyBinding

//메인화면
class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLobbyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}