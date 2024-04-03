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

        val userName = findViewById<EditText>(R.id.editTextText2)
        val userEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val userPassword = findViewById<EditText>(R.id.editTextTextPassword2)
        val userId = findViewById<EditText>(R.id.editTextText3)
        val create_btn2 = findViewById<Button>(R.id.button2)


        create_btn2.setOnClickListener {
            val name = userName.text.toString()
            val email = userEmail.text.toString()
            val id = userId.text.toString()
            val password = userPassword.text.toString()

            addUser(username = name, id = id, password = password, email = email)
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}