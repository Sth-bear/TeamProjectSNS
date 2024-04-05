package com.example.teamproject1

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityLobbyBinding
import com.example.teamproject1.listview.ListAdapter


//메인화면
class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLobbyBinding
    private lateinit var adapter: ListAdapter
    private lateinit var fontChange: FontChange

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        fontChange = FontChange(this)
        applyFontToAllViews(FontManager.getSelectedFont(this))

        val loginId = intent.getStringExtra("loginId")
        binding.tvUserName.text = userList.find { it.id == loginId }?.username
        if (Global.img != null) {
            binding.ivToMyPage.setImageBitmap(Global.img as Bitmap)
        } else {
            userList.find { it.id == loginId }?.userImage?.let {
                binding.ivToMyPage.setImageResource(
                    it
                )
            }
        }
        gotoBanner()
        initPost()

        binding.ivToMyPage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("loginId", loginId)
            startActivity(intent)
        }
    }

    private fun gotoBanner() {
        binding.ivPopUp.setOnClickListener {
            //주소를 변경하고 사용해주세요
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))
            startActivity(intent)
        }
    }

    private fun applyFontToAllViews(selectedFont: String) {
        fontChange.applyFontToTextView(selectedFont, findViewById(android.R.id.content))
    }

    private fun initPost() {
        val postList = PostList.postList
        adapter = ListAdapter(this, postList) //initAdapter
        binding.postListView?.adapter = adapter
        binding.postListView?.setOnItemClickListener { _, _, position, _ -> //parent, view, position, id
            val selectedPost = postList[position]
            //여기서 데이터 넘김
            val intent = Intent(this, PostActivity::class.java)
            intent.putExtra("selectedData", selectedPost)
            startActivity(intent)
        }
    }

    protected override fun onResume() {
        super.onResume()
        if (Global.img != null) {
            binding.ivToMyPage.setImageBitmap(Global.img as Bitmap)
        } else {
            userList.find { it.id == Global.id }?.userImage?.let {
                binding.ivToMyPage.setImageResource(
                    it
                )
            }
        }
        // 로비 액티비티가 다시 시작될 때마다 폰트를 적용합니다.
        applyFontToAllViews(FontManager.getSelectedFont(this))
    }

}
