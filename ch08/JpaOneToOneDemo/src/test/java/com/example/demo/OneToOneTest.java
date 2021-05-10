package com.example.demo;

import com.example.demo.entity.Card;
import com.example.demo.entity.Student;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OneToOneTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardRepository cardRepository;

    @Test
    public void testOneToOne(){
        Student student1 = new Student();
        student1.setName("문별이");
        student1.setSex("female");
        Student student2 = new Student();
        student2.setName("yue_Syuan");
        student2.setSex("female");

        Card card1 = new Card();
        card1.setNum(19921222);

        cardRepository.save(card1);
        studentRepository.save(student1);
        studentRepository.save(student2);

        Card card2 = new Card();
        card2.setNum(19960910);
        cardRepository.save(card2);

        Long id = student1.getId();

        studentRepository.deleteById(id);
    }
}
