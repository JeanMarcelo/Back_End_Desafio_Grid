package com.demo.project.posts

import com.demo.project.user.User
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findByAuthor(author: User, sort: Sort): List<Post>
}

