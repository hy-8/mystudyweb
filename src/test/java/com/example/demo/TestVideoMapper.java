package com.example.demo;

import com.example.demo.dto.Video;
import com.example.demo.mapper.VideoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class TestVideoMapper {
    @Autowired //依赖注入
    private VideoMapper videoMapper;

    @Test
    public void test1(){
        Video v = videoMapper.findByBv("1");
        System.out.println(v.getBv());
        System.out.println(v.getCategory());
        System.out.println(v.getCover());
        System.out.println(v.getIntroduction());
        System.out.println(v.getPublishTime());
        System.out.println(v.getTitle());
        System.out.println(v.getType());
        System.out.println(v.getTagList());
    }

    @Test
    public void test2(){
        Video v=new Video();
        v.setType("转发");
        v.setCategory("动漫");
        v.setTitle("海贼");
        v.setCover("1.png");
        v.setIntroduction("简介...");
        v.setPublishTime(LocalDateTime.now());
        v.setTags("日本_热血");

        videoMapper.insert(v);
        int id = videoMapper.lastInsertId();
        System.out.println(id);
    }

}
