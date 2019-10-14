package com.dave.eregistrar.eregistrar.controller;


import com.dave.eregistrar.eregistrar.model.Student;
import com.dave.eregistrar.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController
{

    @Autowired
    private StudentService studentService;




    @GetMapping(value = {"/","/eregistrar", "/eregistrar/home"})
    public String home()
    {
        return "index";
    }





    @GetMapping(value = {"/eregistrar/student/list"})
    public ModelAndView list(@RequestParam(defaultValue = "0") int pageNo)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.getAllStudentsPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("list");
        return modelAndView;
    }



    @GetMapping(value = {"/eregistrar/student/new"})
    public String displayNewBookForm(Model model) {
        model.addAttribute("student", new Student());
        return "new";
    }

    @PostMapping(value = {"/elibrary/book/new"})
    public String addNewBook(@Valid @ModelAttribute("book") Student student,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "new";
        }
        student = studentService.saveStudent(student);
        return "redirect:/eregistrar/student/list";
    }









    @GetMapping(value = {"/eregistrar/student/edit/{studentId}"})
    public String editBook(@PathVariable Integer studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "edit";
        }
        return "book/list";
    }

    @PostMapping(value = {"/eregistrar/student/edit"})
    public String updateBook(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "edit";
        }
        student = studentService.saveStudent(student);
        return "redirect:/eregistrar/student/list";
    }







    @GetMapping(value = {"/eregistrar/student/delete/{studentId}"})
    public String deleteBook(@PathVariable Integer studentId, Model model) {
        studentService.deleteStudentById(studentId);
        return "redirect:/eregistrar/student/list";
    }


}
