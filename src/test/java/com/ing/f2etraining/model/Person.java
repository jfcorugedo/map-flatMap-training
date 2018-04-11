package com.ing.f2etraining.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Data
@Accessors(chain = true)
public class Person {

    private String name;
    private int age;
    private List<String> skills;

    public Person addSkill(String skill) {
        if(this.skills == null) {
            this.skills = Arrays.asList(skill);
        } else {
            this.skills.add(skill);
        }

        return this;
    }
}
