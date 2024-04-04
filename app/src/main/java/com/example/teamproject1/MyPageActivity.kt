package com.example.teamproject1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityMyPageBinding
import java.io.IOException

//개인정보
class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    //사진지정개수
    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        animationOpen()
        setUpLogOut()

        val loginId = intent.getStringExtra("loginId")
        binding.tvName.text = userList.find{it.id == loginId}?.username
        binding.tvEmail.text = userList.find{it.id == loginId}?.email
        userList.find { it.id == loginId }?.userImage?.let { binding.ivUserImage.setImageResource(it) }

        if (Global.img != null) {
            binding.ivUserImage.setImageBitmap(Global.img as Bitmap)
        }

        //프로필 사진 등록 버튼
        binding.btnProfile.setOnClickListener {
            openGallery()
        }

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
            intent.putExtra("loginId", loginId)
            startActivity(intent)
            finish()
        }

    }

    //프로필 사진 교체
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                Global.img = bitmap
                binding.ivUserImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    //로그아웃버튼
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
