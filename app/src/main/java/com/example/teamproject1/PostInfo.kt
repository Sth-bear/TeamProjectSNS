package com.example.teamproject1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class PostInfo(
    val postIndex: Int,
    val id: String,
    val image: Int,
    val postContent: String,
    val name: String,
    val userImage: Int
) : Parcelable

object PostList {
    private var nextIndex: Int = 1
    val postList = mutableListOf<PostInfo>(
        PostInfo(postIndex = 1, id = "test1", image = R.drawable.image_title1,  postContent = "거북목 운동법 알고 계신가요?\n간단한 운동법 알려 드릴게요. 따라해 보세요.\n 1. 바르게 앉은 상태에서 양손을 목덜미에 얹어 깍지를 껴줍니다.\n 2.천장을 보고 머리를 뒤로 젖혀줍니다.\n 3.숨을 내쉬며 고개를 앞으로 숙여주세요.\n4.2,3번을 반복!!\n다들 거북목 조심하셔요!!", name = "터틀넥", userImage = R.drawable.image_profile1),
        PostInfo(postIndex = 2, id = "test2", image = R.drawable.iv_example,  postContent = "오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.", name = "한정혁", userImage = R.drawable.iv_example2),
        PostInfo(postIndex = 3, id = "master", image = R.drawable.popup,  postContent = "새로운 시작, 봄  \n 봄맞이 챌린지를 확인해보세요. \n -> https://springadvertisment.imweb.me \n ellipsis없이 한 줄 더 추가의 경우", name = "Eleven", userImage = R.drawable.toplogo),
        PostInfo(postIndex = 4, id = "test3", image = R.drawable.test2_user_post_image,  postContent = "물결 소리가 청량함과 평온을 선사하는 아침 해변, 내게는 무한한 자유로움을 상상케 해.", name = "설희아", userImage = R.drawable.test2_user_image),
        PostInfo(postIndex = 5, id = "test4", image = R.drawable.test3_user_post_image,  postContent = "쿠크크크크크", name = "한우영", userImage = R.drawable.test3_user_image),
    )

    fun addPost(id: String, image: Int, postContent: String, name: String) {
        val newPost = PostInfo(postIndex = nextIndex,id = id,image = image,postContent = postContent,name = name, userImage = 1)
        postList.add(newPost)
        nextIndex++
    }

    fun showInfo(id:String): Triple<String, Int, Int> {
        val userName = postList.find { it.id == id }?.name ?: "Error"
        val userImage = postList.find { it.id == id }?.userImage ?: -1
        val postImage = postList.find { it.id == id }?.image ?: -1
        return Triple(userName,userImage,postImage)
    }
}
