package com.example.user.repository;

import com.example.user.domain.UserES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryES extends ElasticsearchRepository<UserES, String> {
}
