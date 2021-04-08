package com.ly.tools.entity;

import lombok.Data;

import java.util.Objects;

/**
 * @Author: LY
 * @Date: 2021/4/7 09:40
 * @Description:
 **/
@Data
public class Teacher {

    private Integer id;
    private String name;
    private Integer age;

    public Teacher() {

    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name) && Objects.equals(age, teacher.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public Teacher(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
