package org.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int person_id;
    @NotEmpty(message = "Имя не должно быть пустым...")
    @Size(min=2, max=100, message="Размер имени должен быть от 2 до 100 символов")
    private String name;
    @Min(value = 0, message = "Возраст не может быть менее 0")
    @Max(value=130, message = "Возраст не может быть более 130")
    private int age;

    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
