package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.teamproject1.UserList.addUser
import com.example.teamproject1.databinding.ActivitySingUpBinding

//회원가입
class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignUp2.setOnClickListener {
            addUser(username = binding.etName.text.toString(), id = binding.etId.text.toString(), password = binding.etPW.text.toString(), email = binding.etEmail.text.toString())
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}