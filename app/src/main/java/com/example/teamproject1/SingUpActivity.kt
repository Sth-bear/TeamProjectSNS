package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        checkUserInfo()
    }


    private fun checkUserInfo() {
        binding.btnSignUp2.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputEmail = binding.etEmail.text.toString()
            val inputPW = binding.etPW.text.toString()
            val inputName = binding.etName.text.toString()

            if (listOf(inputId, inputEmail, inputPW, inputName).all { it.isNotBlank() }) { //빈칸검사
                if (binding.etPW.text.toString().length < 3) {
                    Toast.makeText(this, "비밀번호는 4자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                } else {
                    if (checkId(id = inputId)) { // id중복체크
                        if (checkEmail(email = inputEmail)) {//email중복체크
                            addUser(username = inputName, id = inputId, password = inputPW, email = inputEmail)
                            pushUserInfo(inputId, inputPW) // 아이디,비밀번호 SignIn페이지로 넘기기
                        } else {
                            Toast.makeText(this, "이미 사용중인 Email입니다.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "이미 사용중인 ID입니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun pushUserInfo(id: String, pw: String) {
        val intent = Intent(this, SingInActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("pw", pw)
        setResult(RESULT_OK, intent)
        finish()
    }
}