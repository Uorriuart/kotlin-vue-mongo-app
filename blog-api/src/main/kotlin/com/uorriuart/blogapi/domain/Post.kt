package com.uorriuart.blogapi.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "posts")
data class Post(
    @Id
    val id: ObjectId = ObjectId.get(),
    val title: String,
    val description: String
)