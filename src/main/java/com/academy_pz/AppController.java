package com.academy_pz;

import com.academy_pz.student.Student;
import com.academy_pz.student.StudentService;
import com.academy_pz.teacher.Teacher;
import com.academy_pz.teacher.TeacherRepository;
import com.academy_pz.teacher.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    private final StudentService studentService;
    private final TeacherService teacherService;

    public AppController(StudentService studentService, TeacherRepository teacherRepository, TeacherService teacherService) {
        this.studentService = studentService;

        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 2;
        int firstPage = 1;

        Page<Student> page = studentService
                .findPaginated(pageNo, firstPage, pageSize, sortField, sortDir);

        List<Student> students = page.getContent();

        Page<Teacher> pages = teacherService
                .findPaginated(pageNo, firstPage, pageSize, sortField, sortDir);
//
        List<Teacher> teachers = pages.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("teachers", teachers);
        model.addAttribute("students", students);
        return "index";

    }
}
