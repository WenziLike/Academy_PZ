package com.academy_pz.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;

        if (optional.isPresent()) {
            student = optional.get();
        } else {
            throw new IllegalArgumentException(" Employee not found for id :: " + id);
        }
        return student;
    }

    @Override
    public void deleteStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Delete Student!!!" + id));
        studentRepository.delete(student);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int firstPage, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.studentRepository.findAll(pageable);
    }
}
