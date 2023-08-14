package com.example.user.domain;

import lombok.*;
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
@NoArgsConstructor
@AllArgsConstructor
public class UserES {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        private String name;
        private Integer age;
        private String code;
        private Date modificationDate;


        public List<User> findAll() {
                return null;
        }

        public List<User> findAll(Specification<User> userSpecification) {
                return null;
        }

        public static interface IdStep {
                NameStep withId(String id);
        }

        public static interface NameStep {
                AgeStep withName(String name);
        }

        public static interface AgeStep {
                CodeStep withAge(Integer age);
        }

        public static interface CodeStep {
                ModificationDateStep withCode(String code);
        }

        public static interface ModificationDateStep {
                BuildStep withModificationDate(Date modificationDate);
        }

        public static interface BuildStep {
                UserES build();
        }

        public static class Builder implements IdStep, NameStep, AgeStep, CodeStep, ModificationDateStep, BuildStep {
                private String id;
                private String name;
                private Integer age;
                private String code;
                private Date modificationDate;

                private Builder() {
                }

                public static IdStep userES() {
                        return new Builder();
                }

                @Override
                public NameStep withId(String id) {
                        this.id = id;
                        return this;
                }

                @Override
                public AgeStep withName(String name) {
                        this.name = name;
                        return this;
                }

                @Override
                public CodeStep withAge(Integer age) {
                        this.age = age;
                        return this;
                }

                @Override
                public ModificationDateStep withCode(String code) {
                        this.code = code;
                        return this;
                }

                @Override
                public BuildStep withModificationDate(Date modificationDate) {
                        this.modificationDate = modificationDate;
                        return this;
                }

                @Override
                public UserES build() {
                        return new UserES(
                                this.id,
                                this.name,
                                this.age,
                                this.code,
                                this.modificationDate
                        );
                }
        }
}
