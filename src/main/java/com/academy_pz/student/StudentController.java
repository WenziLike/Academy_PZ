package com.academy_pz.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("student")
public class StudentController {

    private static final String PREDIRECT_INDEX = "redirect:/";

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/add")
    public String showAddStudentForm(Student student, Model model) {
        model.addAttribute("student", student);
        return "student/form-new-student";
    }

    @PostMapping("/add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "student/form-new-student";
        }
        studentService.saveStudent(student);
        return PREDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateStudentForm(@PathVariable("id") long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "student/update-student";
    }


    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable("id") long id,
                                @Valid Student student,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            student.setId(id);
            return "student/update-student";
        }

        studentService.saveStudent(student);

        return PREDIRECT_INDEX;

    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        studentService.deleteStudentById(id);
        return PREDIRECT_INDEX;
    }

}
