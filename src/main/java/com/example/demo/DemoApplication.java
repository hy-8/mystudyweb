package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication  //支持 SpringBoot 功能的应用程序
public class DemoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //作用: 把url路径和磁盘路径做映射
    //http://localhost:8080/play/xxx => static/play
    //http://localhost:8080/play/xxx => 磁盘存储的地方
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //        url路径                                     磁盘路径
        registry.addResourceHandler("/play/**").addResourceLocations("file:"+videoPath);
        registry.addResourceHandler("/img/**").addResourceLocations("file:d:\\img\\");
    }
    @Value("${video-path}")  //字符串的值来自配置文件
    private String videoPath;
}
