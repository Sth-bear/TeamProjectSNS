package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.example.teamproject1.databinding.ActivityLobbyBinding

//메인화면
class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLobbyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


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


        binding.ivPostImage.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        binding.ivmyPage.setOnClickListener {

            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
}