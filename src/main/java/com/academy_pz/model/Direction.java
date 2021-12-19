package com.academy_pz.model;

import com.academy_pz.student.Student;

import javax.persistence.*;
import java.util.List;

@Entity
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "directions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> studentList;

    public Direction() {
    }

    public Direction(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "name='" + name + '\'' +
                '}';
    }
}
