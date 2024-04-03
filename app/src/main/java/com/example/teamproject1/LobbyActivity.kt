package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityLobbyBinding

//메인화면
class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLobbyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val loginId = intent.getStringExtra("loginId") //
        binding.tvUserName.text = userList.find { it.id == loginId }?.username
        userList.find{it.id == loginId}?.userImage?.let { binding.ivToMyPage.setImageResource(it) }

        binding.ivDetail1.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra("userId","test1" )
            startActivity(intent)
        }
        binding.ivToMyPage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("loginId", loginId)
            startActivity(intent)
        }
    }

}
fun changeTheme(mode: Int) {
    AppCompatDelegate.setDefaultNightMode(mode)
}