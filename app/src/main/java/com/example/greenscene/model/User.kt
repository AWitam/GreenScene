package com.example.greenscene.model

data class User(
    val id: String = "",
    val isAnonymous: Boolean = true,
    val name : String = "",
    val email : String = "",
    val photoUrl: String = ""
)
