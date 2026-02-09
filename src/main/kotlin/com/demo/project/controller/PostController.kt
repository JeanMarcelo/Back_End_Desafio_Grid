package com.demo.project.controller

import com.demo.project.dto.PostRequestDTO
import com.demo.project.posts.Post
import com.demo.project.posts.PostService
import com.demo.project.user.User
import org.springframework.security.core.Authentication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(
        @RequestBody postRequest: PostRequestDTO, authentication: Authentication ): ResponseEntity<Post> {

        val user = authentication.principal as User

        val post = postService.create(
            title = postRequest.title,
            content = postRequest.content,
            author = user
     )

        return ResponseEntity.status(HttpStatus.CREATED).body(post)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long, authentication: Authentication): ResponseEntity<Void> {

        val user = authentication.principal as User

        postService.delete(id, user)

        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody postBody: PostRequestDTO,
        authentication: Authentication
    ): ResponseEntity<Post> {

        val user = authentication.principal as User

        val updatedPost = postService.updatePost(id, postBody, user)

        return ResponseEntity.ok(updatedPost)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Post> {

        val post = postService.getPostById(id)

        return ResponseEntity.ok(post)
    }

    @GetMapping
    fun getPosts(
        @RequestParam(defaultValue = "all") scope: String,
        @RequestParam(defaultValue = "newest") order: String,
        authentication: Authentication
    ): ResponseEntity<List<Post>> {

        val user = authentication.principal as User

        val posts = postService.getPosts(scope, order, user)

        return ResponseEntity.ok(posts)
    }
}

