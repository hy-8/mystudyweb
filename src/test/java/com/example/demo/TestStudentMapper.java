package com.example.demo;

import com.example.demo.dto.Student;
import com.example.demo.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//单元测试法
//测试mysql语句接口无参无返回值
@SpringBootTest  //单元测试类和spring连续起来
public class TestStudentMapper {

    @Autowired  //这个注解会根据字段类型到spring中找看看有没有这个实现类对象
    StudentMapper studentMapper;

    @Test  //测试入口，可以利用spring依赖注入什么的特性进行测试
    public void test1(){
        System.out.println(1);
        List<Student> all=studentMapper.findAll();
        for (Student stu : all) {
            System.out.println(stu.getId()+" "+stu.getName());
        }
    }

    @Test //一个类可以有多个入口
    public void test2(){
        System.out.println(2);
        Student stu = studentMapper.findById(1);
        System.out.println(stu.getId()+" "+stu.getName());
    }

    @Test //一个类可以有多个入口
    public void test3(){
 //       studentMapper.insert(5,"钱七");
        Student stu=new Student(6,"liuba");
        studentMapper.insert(stu);
    }
    @Test
    public void test4(){
        Student stu=new Student(1,"zhangsan");
        studentMapper.update(stu);
    }

    @Test
    public void test5(){
        studentMapper.delete(5);

    }
}
