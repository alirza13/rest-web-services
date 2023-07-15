package com.alirza.rest.webservices.restwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alirza.rest.webservices.restwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
