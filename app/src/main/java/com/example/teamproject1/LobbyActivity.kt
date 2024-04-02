package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
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
fun changeTheme(mode: Int) {
    AppCompatDelegate.setDefaultNightMode(mode)
}