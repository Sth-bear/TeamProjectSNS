package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.teamproject1.UserList.userList

//로그인
class SingInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login_btn = findViewById<Button>(R.id.btnSighIn)
        val login_id = findViewById<TextView>(R.id.etId)
        val login_pw = findViewById<TextView>(R.id.etPw)
        val create_btn = findViewById<Button>(R.id.btnSighUp)

        login_btn.setOnClickListener {
            val id = login_id.text.toString()
            val pw = login_pw.text.toString()
            if(login(id,pw)) {
                val intent = Intent(this, LobbyActivity::class.java)
                startActivity(intent)
            }
        }

        create_btn.setOnClickListener {
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }
    }
}

fun login(id:String, password: String):Boolean {
    for (user in userList) {
        if(user.id == id && user.password == password)
            return true
    }
    return false
}