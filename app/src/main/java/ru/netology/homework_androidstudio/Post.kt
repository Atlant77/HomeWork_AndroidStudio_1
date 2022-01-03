package ru.netology.homework_androidstudio

data class Post(
    val id : Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Long,
    var reposts: Long,
    var views: Long,
    var likedByMe: Boolean = false
)


