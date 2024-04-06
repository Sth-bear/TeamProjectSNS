package com.example.teamproject1

object UserList {
    val userList = mutableListOf<UserInfo>(
        UserInfo(username = "name1", id = "test", email = "email1", password = "1234", userImage = R.drawable.test, userIntro = "안녕하세요"),
        UserInfo(username = "name2", id = "id2", email = "email2", password = "password2", userImage = R.drawable.test, userIntro = "안녕하세요" ),
        UserInfo(username = "전지우", id = "test1", email = "test1@email.com", password = "1234", userImage = R.drawable.test1_user_image, userIntro = "안녕하세요. 전지우입니다."),
        UserInfo(username = "한정혁", id = "test2", email = "test2@email.com", password = "1234", userImage = R.drawable.iv_example2, userIntro = "안녕하세요"),
        UserInfo(username = "설희아", id = "test3", email = "test3@email.com", password = "1234", userImage = R.drawable.test, userIntro = "안녕하세요"),
        UserInfo(username = "한우영", id = "test4", email = "test4@email.com", password = "1234", userImage = R.drawable.test, userIntro = "안녕하세요"),
        UserInfo(username = "Eleven", id = "master", email = "eleven@email.com", password = "1234", userImage = R.drawable.toplogo, userIntro = "Eleven!")
    )

    fun addUser(username: String, id: String, email:String, password:String){
        val newUser = UserInfo(username, id, email, password, R.drawable.test, "안녕하세요")
        userList.add(newUser)
    }

    fun checkId(id:String):Boolean {
        return !userList.any {it.id == id}
    }

    fun checkEmail(email:String):Boolean {
        return!userList.any { it.email == email }
    }

    fun checkName(id: String): String? {
        return userList.find { it.id == id }?.username
    }

}

object Global {
    var id:String = ""
    var img:Any? = null
}