package com.example.teamproject1

data class PostInfo(
    val postIndex: Int,
    val id: String,
    val image: Int,
    val postContent: String,
    val name: String,
    val userImage: Int
)

object PostList {
    private var nextIndex: Int = 1
    val postList = mutableListOf<PostInfo>(
        PostInfo(postIndex = 1, id = "test1", image = R.drawable.test3_user_post_image,  postContent = "오늘은 날씨가 정말 좋구나 나만 혼자야 ㅠㅠㅠㅠㅠㅠ", name = "전지우", userImage = R.drawable.test1_user_image),
        PostInfo(postIndex = 2, id = "test2", image = R.drawable.iv_example,  postContent = "오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.오늘 날씨가 엄청 덥네요! 패딩은 두고 오세요.", name = "한정혁", userImage = R.drawable.iv_example2),
        PostInfo(postIndex = 3, id = "test3", image = R.drawable.test2_user_post_image,  postContent = "물결 소리가 청량함과 평온을 선사하는 아침 해변, 내게는 무한한 자유로움을 상상케 해.", name = "설희아", userImage = R.drawable.test2_user_image),
        PostInfo(postIndex = 4, id = "test4", image = R.drawable.test3_user_post_image,  postContent = "쿠크크크크크", name = "한우영", userImage = R.drawable.test3_user_image),
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
