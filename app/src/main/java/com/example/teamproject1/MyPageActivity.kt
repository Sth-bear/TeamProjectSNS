package com.example.teamproject1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
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


        //환경설정 팝업
        binding.btnTheme.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {
                    R.id.action_menu1 -> {
                        //여기에 언어변경 코딩 입력해주세요
                        Toast.makeText(this, "언어 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> { //테마 변경
                        val items = arrayOf("라이트 모드", "다크 모드", "사용자 지정")
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
                        true
                    }
                }
            }
            popupMenu.show()
        }

        //뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LobbyActivity::class.java)
            finish()
        }

    }


    private fun setUpLogOut() {
        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun finish() {
        super.finish()
        animationClose()
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
