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
        PostInfo(postIndex = 1, id = "test1", image = R.drawable.test1_user_post_image,  postContent = "오늘은 날씨가 정말 좋구나 나만 혼자야 ㅠㅠㅠㅠㅠㅠ", name = "전지우", userImage = R.drawable.test1_user_image),
        PostInfo(postIndex = 2, id = "test2", image = 123,  postContent = "오늘 날씨가 엄청 덥네요:: 대충 SNS문구", name = "한정혁", userImage = R.drawable.test1_user_image),
        PostInfo(postIndex = 3, id = "test3", image = 12,  postContent = "물결 소리가 청량함과 평온을 선사하는 아침 해변, 내게는 무한한 자유로움을 상상케 해.", name = "설희아", userImage = R.drawable.test1_user_image),
        PostInfo(postIndex = 4, id = "test4", image = 123,  postContent = "시간은 마치 바람 같아, 잡히지 않지만 느껴지는 순간, 그 순간이 소중한 거야.", name = "한우영", userImage = R.drawable.test1_user_image),
    )

    fun addPost(id: String, image: Int, postContent: String, name: String) {
        val newPost = PostInfo(postIndex = nextIndex,id = id,image = image,postContent = postContent,name = name, userImage = 1)
        postList.add(newPost)
        nextIndex++
    }
}
