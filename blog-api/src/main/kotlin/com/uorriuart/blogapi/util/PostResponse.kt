package com.uorriuart.blogapi.util

import com.uorriuart.blogapi.domain.Post

class PostResponse(
    val id: String,
    val title: String,
    val description: String,
) {
    companion object {
        fun fromEntity(post: Post): PostResponse =
            PostResponse(
                id = post.id.toHexString(),
                title = post.title,
                description = post.description
            )
    }
}