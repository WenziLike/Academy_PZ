package com.academy_pz.student;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudent();

    void saveStudent(Student student);

    Student getStudentById(long id);

    void deleteStudentById(long id);

    Page<Student> findPaginated(int pageNo, int firstPage, int pageSize, String sortField, String sortDirection);
}
