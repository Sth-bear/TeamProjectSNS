package com.example.teamproject1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import com.example.teamproject1.databinding.ActivityMyPageBinding
import java.io.IOException

//개인정보
class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    //프로필 사진 업로드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBack.setOnClickListener{
            val intent = Intent(this, LobbyActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfile.setOnClickListener {
            openGallery()
        }
    }

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
                binding.ivUserImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}