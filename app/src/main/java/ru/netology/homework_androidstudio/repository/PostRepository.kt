package ru.netology.homework_androidstudio.repository

import androidx.lifecycle.LiveData
import ru.netology.homework_androidstudio.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun repostAdd()
}