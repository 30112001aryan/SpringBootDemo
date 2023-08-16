package com.example.user.repository;

import com.example.user.domain.UserES;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryES extends ElasticsearchRepository<UserES, String> {
    List<UserES> getByCode(String code);
    List<UserES> findByNameContaining(String name);
}
