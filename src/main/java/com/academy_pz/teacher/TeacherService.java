package com.academy_pz.teacher;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    List<Teacher> getAllTeacher();

    void saveTeacher(Teacher teacher);

    Teacher getTeacherById(long id);

    void deleteTeacherById(long id);

    Page<Teacher> findPaginated(int pageNo, int firstPage, int pageSize, String sortField, String sortDirection);
}
