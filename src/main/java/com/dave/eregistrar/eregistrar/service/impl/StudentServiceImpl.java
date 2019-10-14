package com.dave.eregistrar.eregistrar.service.impl;

import com.dave.eregistrar.eregistrar.model.Student;
import com.dave.eregistrar.eregistrar.repository.StudentRepository;
import com.dave.eregistrar.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService
{

    @Autowired
    StudentRepository studentRepository;


    @Override
    public Page<Student> getAllStudentsPaged(int pageno) {
        return studentRepository.findAll(PageRequest.of(pageno,10));
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudentById(Integer id)
    {
        studentRepository.deleteById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
