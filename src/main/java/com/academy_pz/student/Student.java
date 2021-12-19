package com.academy_pz.student;

import com.academy_pz.model.Direction;
import com.academy_pz.teacher.Teacher;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_student", joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private List<Teacher> teacherList;

//        @NotEmpty(message = "Direction cannot be empty.")
//    @Size(max = 2048, message = "Direction too long (more than 2kB)")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direction_id")
    private Direction directions;

    public Student() {
    }

    public Student(String firstName,
                   String lastName,
                   Integer age,
                   String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
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

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Direction getDirections() {
        return directions;
    }

    public void setDirections(Direction directions) {
        this.directions = directions;
    }
}
