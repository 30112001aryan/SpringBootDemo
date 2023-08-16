package com.example.user.repository;

import com.example.user.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Vector;

public interface UserRepository extends JpaRepository<User,String> {

    @Query(value = "select * from user where name like %?1% ", nativeQuery = true)
    List<User> getByName(String name);

}
