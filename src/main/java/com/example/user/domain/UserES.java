package com.example.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

@Document(indexName = "user")
@Setter
@Data
@Builder
public class UserES {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        private String name;
        private Integer age;
        @UpdateTimestamp
        private Date modificationDate;


        public List<User> findAll() {
                return null;
        }

        public List<User> findAll(Specification<User> userSpecification) {
                return null;
        }
}
