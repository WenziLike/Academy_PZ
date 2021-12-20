package com.academy_pz.teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(long id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        Teacher teacher = null;

        if (optional.isPresent()) {
            teacher = optional.get();
        } else {
            throw new IllegalArgumentException(" Employee not found for id :: " + id);
        }

        return teacher;
    }

    @Override
    public void deleteTeacherById(long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Delete Student!!! " + id));
        teacherRepository.delete(teacher);
    }

    @Override
    public Page<Teacher> findPaginated(int pageNo, int firstPage, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return teacherRepository.findAll(pageable);
    }
}
