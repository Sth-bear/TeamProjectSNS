package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.teamproject1.UserList.addUser
import com.example.teamproject1.UserList.checkEmail
import com.example.teamproject1.UserList.checkId
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
            if (binding.etId.text.toString().isNotBlank() && binding.etEmail.text.toString().isNotBlank() && binding.etPW.text.toString().isNotBlank() && binding.etName.text.toString().isNotBlank()) { //빈칸검사
                if (binding.etPW.text.toString().length < 3) {
                    Toast.makeText(this, "비밀번호는 4자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                } else {
                    if (checkId(id = binding.etId.text.toString())) { // id중복체크
                        if (checkEmail(email = binding.etEmail.text.toString())) {//email중복체크
                            addUser(
                                username = binding.etName.text.toString(),
                                id = binding.etId.text.toString(),
                                password = binding.etPW.text.toString(),
                                email = binding.etEmail.text.toString()
                            )
                            val intent = Intent(this, SingInActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "이미 사용중인 Email입니다.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "이미 사용중인 ID입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}