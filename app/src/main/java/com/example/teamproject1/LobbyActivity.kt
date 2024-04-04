package com.example.teamproject1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teamproject1.UserList.userList
import com.example.teamproject1.databinding.ActivityLobbyBinding
import com.example.teamproject1.listview.ListAdapter


//메인화면
class LobbyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLobbyBinding
    private lateinit var adapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val loginId = intent.getStringExtra("loginId")
        binding.tvUserName.text = userList.find { it.id == loginId }?.username
        userList.find { it.id == loginId }?.userImage?.let { binding.ivToMyPage.setImageResource(it) }


        initPost()

        /*
                //1번게시글
                val (page1UserName,page1UserImage,page1PostImage) = showInfo("test2")
                binding.tvName1.text = page1UserName
                binding.ivUserImage1.setImageResource(page1UserImage)
                binding.ivDetail1.setImageResource(page1PostImage)
                binding.ivDetail1.setOnClickListener {
                    binding.ivDetail.setOnClickListener {
                    val intent = Intent(this, PostActivity::class.java)
                    intent.putExtra("userId","test2" )
                    startActivity(intent)

                }
                val(page2UserName,page2UserImage,page2PostImage) = showInfo("test3")
                binding.tvName2.text = page2UserName
                binding.ivUserImage2.setImageResource(page2UserImage)
                binding.ivDetail2.setImageResource(page2PostImage)
                binding.ivDetail2.setOnClickListener {
                    val intent = Intent(this, PostActivity::class.java)
                    intent.putExtra("userId", "test3")
                    startActivity(intent)
                }

                val(page3UserName,page3UserImage,page3PostImage) = showInfo("test4")
                binding.tvName3.text = page3UserName
                binding.ivUserImage3.setImageResource(page3UserImage)
                binding.ivDetail3.setImageResource(page3PostImage)
                binding.ivDetail3.setOnClickListener {
                    val intent = Intent(this, PostActivity::class.java)
                    intent.putExtra("userId", "test4")
                    startActivity(intent)
                }*/



        binding.ivToMyPage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("loginId", loginId)
            startActivity(intent)
        }
    }

    private fun initPost() {
        val postList = PostList.postList
        adapter = ListAdapter(this, postList) //initAdapter
        binding.postListView.adapter = adapter
        binding.postListView.setOnItemClickListener { _, _, position, _ -> //parent, view, position, id
            val selectedPost = postList[position]
            //여기서 데이터 넘김
            val intent = Intent(this, PostActivity::class.java).apply {
                putExtra("selectedData", selectedPost)
            }
            startActivity(intent)
        }
    }





/*    private fun pushUserInfo(Image: Any, name: String, postImage: Any, comment: String) {
        val intent = Intent(this, PostActivity::class.java)
        intent.putExtra("UserImage", Image)
        intent.putExtra("Name", name)
        intent.putExtra("PostImage", postImage)
        intent.putExtra("Comment", comment
        )
        setResult(RESULT_OK, intent)
        finish()
    }*/
}
