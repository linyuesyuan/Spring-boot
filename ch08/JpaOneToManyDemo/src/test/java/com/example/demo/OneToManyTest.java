package com.example.demo;

import com.example.demo.entity.School;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.TeacherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToManyTest {
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void add(){
        School school1 = new School();
        school1.setName("TKU");
        schoolRepository.save(school1);

        Teacher teacher1 = new Teacher();
        teacher1.setName("廖賀田");
        teacher1.setSchool(school1);
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setName("test2");
        teacher2.setSchool(school1);
        teacherRepository.save(teacher2);

        Teacher teacher3 = new Teacher();
        teacher3.setName("test3");
        teacher3.setSchool(school1);
        teacherRepository.save(teacher3);

        Teacher teacher4 = new Teacher();
        teacher4.setName("test4");
        teacher4.setSchool(school1);
        teacherRepository.save(teacher4);
    }

    @Test
    public void find(){
        School school1 = schoolRepository.findSchoolById(4);
        List<Teacher> teacherList = school1.getTeacherList();
        System.out.println(school1.getName());
        if(teacherList != null){
            for (Teacher teacher : teacherList){
                System.out.println(teacher.getName());
            }
        }
    }

    @Test
    public void deleteSchoolById(){
        schoolRepository.deleteById(1);
    }

    @Test
    public void deleteTeacherById(){
        teacherRepository.deleteById(1);
    }
}
