package com.example.teamproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityMyPageBinding

//개인정보
class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        animationOpen()
        setUpLogOut()

        val loginId = intent.getStringExtra("loginId")
        binding.tvId.text = loginId
        binding.tvEmail.text = userList.find{it.id == loginId}?.email
        userList.find { it.id == loginId }?.userImage?.let { binding.ivUserImage.setImageResource(it) }


        changeTheme()

        //뒤로가기 버튼
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            finish()
        }
    }
    override fun finish() {
        super.finish()
        animationClose()
    }
    private fun changeTheme(){
        binding.btnTheme.setOnClickListener {
            val items = arrayOf("라이트 모드", "다크 모드", "시스템 지정")
            val builder = AlertDialog.Builder(this)
                .setTitle("테마 변경")
                .setItems(items) { _, id ->
                    if (items[id] == "라이트 모드") {
                        changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    } else if (items[id] == "다크 모드") {
                        changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
            builder.show()
        }
    }


    private fun setUpLogOut() {
        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }


    private fun animationOpen() {
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.slide_right_enter, R.anim.slide_none)
        } else {
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_none)
        }
    }

    private fun animationClose() {
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE, R.anim.slide_none, R.anim.slide_right_exit)
        } else {
            overridePendingTransition(R.anim.slide_none, R.anim.slide_right_exit)
        }
    }
}
