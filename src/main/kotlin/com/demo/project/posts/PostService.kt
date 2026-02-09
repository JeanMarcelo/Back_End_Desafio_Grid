package com.demo.project.posts


import com.demo.project.dto.PostRequestDTO
import com.demo.project.user.User
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class PostService(
    private val postRepository: PostRepository,
) {

    fun create(
        title: String, content: String, author: User
    ): Post {

        val post = Post(
            title = title,
            content = content,
            author = author
        )

        return postRepository.save(post)
    }
    fun delete(postId: Long, user: User) {

        val post = postRepository.findById(postId)
            .orElseThrow { RuntimeException("Post not found") }

        if (post.author.id != user.id) {
            throw RuntimeException("You can not delete this post")
        }
        postRepository.delete(post)
    }

    fun updatePost(postId: Long, change: PostRequestDTO, user: User): Post {

        val post = postRepository.findById(postId)
            .orElseThrow { RuntimeException("Post not found") }

        if (post.author.id != user.id) {
            throw RuntimeException("You are not the author of this post")
        }

        post.title = change.title
        post.content = change.content

        return postRepository.save(post)
    }

    fun getPostById(postId: Long): Post {
        return postRepository.findById(postId)
            .orElseThrow { RuntimeException("Post not found") }
    }

    fun getPosts(scope: String, order: String, user: User): List<Post> {

        val sort = if (order == "oldest")
            Sort.by("createdAt").ascending()
        else
            Sort.by("createdAt").descending()

        return when (scope) {
            "me" -> postRepository.findByAuthor(user, sort)
            else -> postRepository.findAll(sort)
        }
    }

}

