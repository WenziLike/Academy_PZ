package com.academy_pz.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    private static final String PREDIRECT_INDEX = "redirect:/";

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/add")
    public String showAddTeacherForm(Teacher teacher, Model model) {
        model.addAttribute("teacher", teacher);
        return "teacher/form-new-teacher";
    }

    @PostMapping("/add")
    public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "teacher/form-new-teacher";
        }
        teacherService.saveTeacher(teacher);
        return PREDIRECT_INDEX;
    }

    @GetMapping("/edit/{id}")
    public String showUpdateTeacherForm(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/update-teacher";
    }

    @PostMapping("/edit/{id}")
    public String updateTeacher(@PathVariable("id") long id,
                                @Valid Teacher teacher,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            teacher.setId(id);
            return "teacher/update-teacher";
        }
        teacherService.saveTeacher(teacher);
        return PREDIRECT_INDEX;
    }


    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teacherService.deleteTeacherById(id);
        return PREDIRECT_INDEX;
    }


}
