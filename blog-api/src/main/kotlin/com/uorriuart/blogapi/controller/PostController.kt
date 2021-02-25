package com.uorriuart.blogapi.controller

import com.uorriuart.blogapi.domain.Post
import com.uorriuart.blogapi.domain.PostRequest
import com.uorriuart.blogapi.repository.PostRepository
import com.uorriuart.blogapi.util.PostResponse
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:8081"])
@RestController
@RequestMapping("/api/posts")
class PostController(private val postRepository: PostRepository) {

    @GetMapping
    fun getAllPosts(@RequestParam(required = false) title: String?): ResponseEntity<List<PostResponse>> {
        val posts: List<Post> = if (title == null) {
            postRepository.findAll()
        } else
            postRepository.findByTitle(title)

        return ResponseEntity.ok(
            posts.map { PostResponse.fromEntity(it) }
        )
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable("id") id: String): ResponseEntity<PostResponse> {
        val post = postRepository.findById(ObjectId(id))

        return ResponseEntity.ok(
            PostResponse.fromEntity(post)
        )
    }

    @PostMapping
    fun createPost(@RequestBody request: PostRequest): ResponseEntity<PostResponse> {
        val post = postRepository.save(
            Post(
                title = request.title,
                description = request.description
            )
        )

        return ResponseEntity(
            PostResponse.fromEntity(post), HttpStatus.CREATED
        )
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable("id") id: String, @RequestBody request: PostRequest): ResponseEntity<PostResponse> {
        val post = postRepository.findById(ObjectId(id))
        val updatedPost = postRepository.save(
            Post(
                id = post.id,
                title = request.title,
                description = request.description
            )
        )

        return ResponseEntity.ok(
            PostResponse.fromEntity(updatedPost)
        )
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable("id") id: String): ResponseEntity<Unit> {
        postRepository.deleteById(ObjectId(id))

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteAllPosts(): ResponseEntity<Unit> {
        postRepository.deleteAll()

        return ResponseEntity.noContent().build()
    }
}
