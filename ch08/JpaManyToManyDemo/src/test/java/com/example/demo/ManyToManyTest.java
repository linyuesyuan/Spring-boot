package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ManyToManyTest {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void add(){
        Set<Teacher> teachers = new HashSet<>();
        Set<Student> students = new HashSet<>();
        Student student1 = new Student();
        student1.setName("yue");
        students.add(student1);
        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("jean");
        students.add(student2);
        studentRepository.save(student2);

        Teacher teacher1 = new Teacher();
        teacher1.setName("武田老師");
        teacher1.setStudents(students);
        teachers.add(teacher1);
        teacherRepository.save(teacher1);
    }
}
