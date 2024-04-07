package com.example.teamproject1

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.teamproject1.PostList.postList
import com.example.teamproject1.PostList.showInfo
import com.example.teamproject1.databinding.ActivityMainBinding
import com.example.teamproject1.databinding.ActivityPostBinding
import java.io.Serializable

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var fontChange: FontChange
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setViewMore()
        goBack()
        setUpFont()

        val selectedPost = intent.getParcelableExtra("selectedData", PostInfo::class.java)
        selectedPost?.let { putEachData(it) }

    }

    private fun setUpFont() {
        fontChange = FontChange(this)
        applyFontToAllViews(FontManager.getSelectedFont(this))
    }



    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun putEachData(post: PostInfo) {
        binding.ivUserImage.setImageResource(post.userImage)
        binding.tvName.text = post.name
        binding.ivPostImage.setImageResource(post.image)
        binding.tvTitle.text = post.postContent
    }

    private fun setViewMore() {
        val tvTitle = binding.tvTitle //게시글
        val tvMore = binding.tvMore //더보기

        tvTitle.post {
            val titleLineCount = tvTitle.layout.lineCount
            val titleMaxLine = tvTitle.maxLines

            if (tvTitle.layout.getEllipsisCount(titleLineCount - 1) > 0 || titleLineCount >= titleMaxLine) {
                tvMore.visibility = View.VISIBLE
                tvMore.setOnClickListener {
                    if (tvMore.text == getString(R.string.more)) {
                        tvTitle.maxLines = Int.MAX_VALUE
                        tvMore.text = getString(R.string.less)
                    } else {
                        tvTitle.maxLines = 3
                        tvMore.text = getString(R.string.more)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 로비 액티비티가 다시 시작될 때마다 폰트를 적용합니다.
        applyFontToAllViews(FontManager.getSelectedFont(this))
    }

    private fun applyFontToAllViews(selectedFont: String) {
        fontChange.applyFontToTextView(selectedFont, binding.root)
    }

}