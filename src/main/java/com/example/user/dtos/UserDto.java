package com.example.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private String code;
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
        UserDto build();
    }


    public static class Builder implements IdStep, NameStep, AgeStep, CodeStep, ModificationDateStep, BuildStep {
        private String id;
        private String name;
        private Integer age;
        private String code;
        private Date modificationDate;

        private Builder() {
        }

        public static IdStep userDto() {
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
        public UserDto build() {
            return new UserDto(
                    this.id,
                    this.name,
                    this.age,
                    this.code,
                    this.modificationDate
            );
        }
    }
}
