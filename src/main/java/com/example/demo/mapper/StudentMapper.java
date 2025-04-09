package com.example.demo.mapper;

import com.example.demo.dto.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
//使用mybatis两个条件：1.在xml中添加依赖，注意版本。
//                    2.在resources下加一个application.properties填写数据库信息
//使用的时候写一个javabean，字段和表中的信息：类名和表名，字段和属性
//StudentMapper接口，加一个注解，然后实现增删改查注意注解，多个参数可以使用一个对象代替
@Mapper  //这不是一个普通接口，专用于增删改查
//需要一个实现类(mybatis和spring共同提供)可以通过@Autowired依赖注入获取是实现类对象
public interface StudentMapper {
    @Select("""
            select id, name
            from student
            """)
    List<Student> findAll();

    //根据编号查询学生
    //mybatis提供语法：你要获取方法参数值，用#{}，里面写上参数
    @Select("""
            select id, name
            from student
            where id=#{id}
            """)
    Student findById(int id);

//    @Insert("""
//            insert into student(id,name) values
//            (#{id},#{name})
//            """)
//    //i的值会替换#{id}id的值
//    void insert(@Param("id")int i, @Param("name")String n);

    @Insert("""
            insert into student(id,name) values
            (#{id},#{name})
            """)
    void insert(Student stu);//对调用student的javabean里的get，set方法赋值

    @Update("""
            update student set name=#{name} 
            where id=#{id};
            """)
    void update(Student stu);

    @Delete("""
            delete from student where id=#{id};
            """)
    void delete(int id);
}
