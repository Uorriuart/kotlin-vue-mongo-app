package com.uorriuart.blogapi.repository

import com.uorriuart.blogapi.domain.Post
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, String> {
    fun findById(id: ObjectId): Post
    fun deleteById(id: ObjectId)
    fun findByTitle(title: String): List<Post>
}