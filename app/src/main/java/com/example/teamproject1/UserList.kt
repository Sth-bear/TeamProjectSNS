package com.example.teamproject1

object UserList {
    val userList = mutableListOf<UserInfo>(
        UserInfo(username = "name1", id = "test", email = "email1", password = "1234"),
        UserInfo(username = "name2", id = "id2", email = "email2", password = "password2")
    )

    fun addUser(username: String, id: String, email:String, password:String){
        val newUser = UserInfo(username, id, email, password)
        userList.add(newUser)
    }

}