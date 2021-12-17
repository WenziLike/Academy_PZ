package com.academy_pz.student;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name cannot be empty.")
    @Size(min = 2, max = 24, message = "Size must be between 2 and 24")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty.")
    @Size(min = 2, max = 24, message = "Size must be between 2 and 24")
    private String lastName;

    @NotNull(message = "User's age cannot be null.")
    @Min(value = 18, message = "Must be at least 18")
    private Integer age;

    @Email(message = "Email is not correct")
    @NotEmpty(message = "User's email cannot be empty.")
    private String email;

    @NotEmpty(message = "Direction cannot be empty.")
    @Size(max = 2048, message = "Direction too long (more than 2kB)")
    private String direction;

    public Student() {
    }

    public Student(String firstName,
                   String lastName,
                   Integer age,
                   String email,
                   String direction) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.direction = direction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
