package com.dave.eregistrar.eregistrar.service;

import com.dave.eregistrar.eregistrar.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService
{
public abstract Page<Student> getAllStudentsPaged(int pageno);
public abstract Student getStudentById(Integer id);
public abstract void deleteStudentById(Integer id);
public abstract Student saveStudent(Student student);
}
