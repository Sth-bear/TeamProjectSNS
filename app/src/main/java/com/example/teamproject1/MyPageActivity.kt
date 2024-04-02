package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.example.teamproject1.databinding.ActivityMyPageBinding

//개인정보
class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        overrideActivityTransition(OVERRIDE_TRANSITION_OPEN,R.anim.slide_right_enter,R.anim.slide_none)
        setUpLogOut()
        


        //환경설정 팝업
        binding.btnSetUp.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->

                when (menuItem.itemId) {
                    R.id.action_menu1 -> {
                        //여기에 언어변경 코딩 입력해주세요
                        Toast.makeText(this, "언어 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> {
                        //여기에 야간모드 코딩 입력해주세요
                        Toast.makeText(this, "야간모드로 변경되었습니다", Toast.LENGTH_SHORT).show()
                        true
                    }
                }
            }
            popupMenu.show()
        }

        //뒤로가기 버튼
        binding.btnBack.setOnClickListener{
            val intent = Intent(this, LobbyActivity::class.java)
            finish()
        }

    }
    private fun setUpLogOut() {
        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, SingInActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }
    }

    override fun finish() {
        super.finish()
        overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE,R.anim.slide_none,R.anim.slide_right_exit)
    }

}