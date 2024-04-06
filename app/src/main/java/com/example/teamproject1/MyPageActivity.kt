package com.example.teamproject1

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityMyPageBinding
import java.io.IOException

//개인정보
class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding
    private lateinit var fontChange: FontChange
    private lateinit var fontManager: FontManager

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
        if (Global.img != null) {
            binding.ivUserImage.setImageBitmap(Global.img as Bitmap)
        } else {
            userList.find { it.id == loginId }?.userImage?.let { binding.ivUserImage.setImageResource(it) }
        }

        //프로필 사진 등록 버튼
        binding.btnProfile.setOnClickListener {
            openGallery()
        }

        onClickFontButton()
        fontChange = FontChange(this)

        changeLanguage()
        changeScreen()
        changeTheme()

        //뒤로가기 버튼
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        super.finish()
        animationClose()
    }

    private fun changeLanguage() {
        val kor = getText(R.string.kor)
        val eng = getText(R.string.eng)
        val lang = getText(R.string.lang)
        val koLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("ko-KR")
        val enLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("en-US")

        binding.btnLanguage.setOnClickListener {
            val items = arrayOf("$kor", "$eng")
            val builder = AlertDialog.Builder(this)
                .setTitle("$lang")
                .setItems(items) { _, id ->
                    if (items[id] == "$kor") {
                        AppCompatDelegate.setApplicationLocales(koLocale)
                    } else {
                        AppCompatDelegate.setApplicationLocales(enLocale)
                    }
                }
            builder.show()
        }
    }

    private fun changeScreen() {
        val garo = getText(R.string.garo)
        val sero = getText(R.string.sero)
        val screen = getText(R.string.screen)

        binding.btnScreen.setOnClickListener {
            val items = arrayOf("$garo", "$sero")
            val builder = AlertDialog.Builder(this)
                .setTitle("$screen")
                .setItems(items) { _, id ->
                    if (items[id] == "$garo") {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    } else {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    }
                }
            builder.show()
        }
    }

    private fun changeTheme(){
        val light = getText(R.string.light)
        val dark = getText(R.string.dark)
        val system = getText(R.string.system)
        val theme = getText(R.string.theme)

        binding.btnTheme.setOnClickListener {
            val items = arrayOf("$light", "$dark", "$system")
            val builder = AlertDialog.Builder(this)
                .setTitle("$theme")
                .setItems(items) { _, id ->
                    if (items[id] == "$light") {
                        changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    } else if (items[id] == "$dark") {
                        changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    } else {
                        changeTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
            builder.show()
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
            Global.img = null
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
    private fun changeTheme(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun onClickFontButton() {
        val bukk = getString(R.string.bukk)
        val snow = getString(R.string.snow)
        val chsun = getString(R.string.chosun)
        val ongle = getString(R.string.ongle)
        val fontOptions = arrayOf("$bukk", "$snow", "$chsun", "$ongle")
        binding.btnFont?.setOnClickListener {
            fontChange.showFontSelectionDialog(fontOptions) { selectedFont ->
                // 폰트 변경 후 저장
                FontManager.setSelectedFont(this, selectedFont)
                applyFontToAllViews(FontManager.getSelectedFont(this))


            }
        }
    }
    private fun applyFontToAllViews(selectedFont: String) {
        val fontChange = FontChange(this)
        fontChange.applyFontToTextView(selectedFont, binding.root)
    }
}
