package com.example.teamproject1

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject1.databinding.ActivityDetailBinding
import com.example.teamproject1.UserList.checkName
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.PostList.postList

//타인 디테일 페이지
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //animation open
        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, R.anim.slide_right_enter, R.anim.slide_none)

        val userId = intent.getStringExtra("userId")
        val userName = userId?.let { checkName(it) }
        val userImage =  userList.find{it .id == userId}?.userImage
        val userIntro = userList.find{it.id == userId}?.userIntro

        if (userImage != null) {
            binding.ivUserImage.setImageResource(userImage)
            binding.ivUserImage2.setImageResource(userImage)
        }
        if (userIntro != null) {
            binding.tvIntro.text = userIntro
        }
        binding.tvId.text = userId
        binding.tvId2.text = userId

        postList.find { it.id == userId}?.image?.let { binding.ivDetail.setImageResource(it) }

        binding.btnBack.setOnClickListener {
            finish()
        }






    }

    override fun finish() {
        super.finish()
        //animation close
        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE, R.anim.slide_none, R.anim.slide_right_exit)
    }
}