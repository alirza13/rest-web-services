package com.alirza.rest.webservices.restwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alirza.rest.webservices.restwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
