package com.example.demo;

import com.example.demo.dto.Video;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/*
测试文件的读取
* */
public class TestFile {
    public static void main(String[] args) throws IOException {
        //Files 包含都是跟文件操作相关的方法（读取文件内容，拷贝文件，删除文件...）
        //Path  代表的是文件存储路径

        //Path的书写：1.绝对路径
//        List<String> lines = Files.readAllLines(
//                Path.of("D:\\java\\learnjava\\data","p.csv"));
        //2.相对路径 D:\java\learnjava 运行程序目录的起点，相对路径其实就是省略了起点的部分路径
                List<String> lines = Files.readAllLines(
                Path.of("data","p.csv"));
        //String line 就是读取的文件中的每一行
        for (String line : lines) {
      //      System.out.println(line);
            String[] s=line.split(",");
            String[] tags=s[7].split("_");
            System.out.println(s[0] + " "+ s[6]+" "+tags[0]+" "+tags[1]);
              new Video(s[0],s[3], LocalDateTime.parse(s[6]),s[4],s[5], Arrays.asList(tags),List.of(),s[1],s[2]);
        }
    }
}
