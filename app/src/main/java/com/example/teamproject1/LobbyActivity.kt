package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.teamproject1.PostList.postList
import com.example.teamproject1.PostList.showInfo
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityLobbyBinding
import de.hdodenhof.circleimageview.CircleImageView

//메인화면
class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLobbyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val loginId = intent.getStringExtra("loginId")
        binding.tvUserName.text = userList.find { it.id == loginId }?.username
        userList.find{it.id == loginId}?.userImage?.let { binding.ivToMyPage.setImageResource(it) }

        //1번게시글
        val (page1UserName,page1UserImage,page1PostImage) = showInfo("test2")
        binding.tvName1.text = page1UserName
        binding.ivUserImage1.setImageResource(page1UserImage)
        binding.ivDetail1.setBackgroundResource(page1PostImage)
        binding.ivDetail1.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra("userId","test2" )
            startActivity(intent)
        }
        val(page2UserName,page2UserImage,page2PostImage) = showInfo("test3")
        binding.tvName2.text = page2UserName
        binding.ivUserImage2.setImageResource(page2UserImage)
        binding.ivDetail2.setBackgroundResource(page2PostImage)
        binding.ivDetail2.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra("userId", "test3")
            startActivity(intent)
        }

        val(page3UserName,page3UserImage,page3PostImage) = showInfo("test4")
        binding.tvName3.text = page3UserName
        binding.ivUserImage3.setImageResource(page3UserImage)
        binding.ivDetail3.setBackgroundResource(page3PostImage)
        binding.ivDetail3.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra("userId", "test4")
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