package com.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Data

@Entity(name="user")
@Table(name="user")
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="age")
    private Integer age;
    @Column(name="code")
    private String code;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="modification_date")
    private Date modificationDate;

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
        User build();
    }


    public static class Builder implements IdStep, NameStep, AgeStep, CodeStep, ModificationDateStep, BuildStep {
        private String id;
        private String name;
        private Integer age;
        private String code;
        private Date modificationDate;

        private Builder() {
        }

        public static IdStep user() {
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
        public User build() {
            return new User(
                    this.id,
                    this.name,
                    this.age,
                    this.code,
                    this.modificationDate
            );
        }
    }
}
