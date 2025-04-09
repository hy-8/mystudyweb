package com.example.demo.controller;

import com.example.demo.dto.Play;
import com.example.demo.dto.Video;
import com.example.demo.service.VideoService1;
import com.example.demo.service.VideoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

//路径参数

//1.@RequestMapping("/video/{bv}")
//2. 给控制器里的方法加参数，类型和我们之前定义的类型一直(string),名字和路径{bv}一致
//@PathVariable String bv  这个注解表示参数值从路径{bv}获取
@Controller
public class VideoController {

    @RequestMapping("/video/{bv}")
    @ResponseBody
    public Video t(@PathVariable String bv) {
//        if(bv.equals("1")){
//            List<Play> plays= List.of(
//                    new Play("p1","二分查找-演示", LocalTime.parse("00:05:46"),"1_1.mp4"),
//                    new Play("p2","二分查找-实现", LocalTime.parse("00:06:47"),"1_2.mp4")
//            );
//            return new Video("1","面试专题基础篇", LocalDateTime.now(),"1.png","祝你面试游刃有余",
//                    List.of("面试","Java","计算机技术"),plays,"自制","科技->计算机技术");
//        }
//        if(bv.equals("2")){
//            List<Play> plays= List.of(
//                    new Play("p1","java中的线程状态", LocalTime.parse("00:09:45"),"2_1.mp4"),
//                    new Play("p2","代码演示1", LocalTime.parse("00:07:05"),"2_2.mp4"),
//                    new Play("p3","代码演示2", LocalTime.parse("00:05:01"),"2_3.mp4")
//            );
//            return new Video("2","面试专题并发篇", LocalDateTime.now(),"2.png","祝你面试游刃有余",
//                    List.of("面试","Java","计算机技术"),plays,"自制","科技->计算机技术");
//        }
//        return null;
        return videoService2.find(bv);
    }
    @Autowired
    private VideoService1 videoService1;

    @Autowired
    private VideoService2 videoService2;

    @RequestMapping("/publish")
    @ResponseBody //把java中的map对象什么的对象转成json数据传输与前端通信
    //@RequestBody注解把json数据转成java对象
    public Map<String,String> publish(@RequestBody Video video){
        String bv=videoService2.publish(video);
        return Map.of("bv",bv);
    }
}
//    @RequestMapping("/video/1")
//    @ResponseBody
//    public Video t1(){
//        List<Play> plays= List.of(
//                new Play("p1","二分查找-演示", LocalTime.parse("00:05:46"),"1_1.mp4"),
//                new Play("p2","二分查找-实现", LocalTime.parse("00:06:47"),"1_2.mp4")
//        );
//        return new Video("1","面试专题基础篇", LocalDateTime.now(),"1.png","祝你面试游刃有余",
//                 List.of("面试","Java","计算机技术"),plays,"自制","科技->计算机技术");
//    }
//
//
//    @RequestMapping("/video/2")
//    @ResponseBody
//    public Video t2(){
//        List<Play> plays= List.of(
//                new Play("p1","java中的线程状态", LocalTime.parse("00:09:45"),"2_1.mp4"),
//                new Play("p2","代码演示1", LocalTime.parse("00:07:05"),"2_2.mp4"),
//                new Play("p3","代码演示2", LocalTime.parse("00:05:01"),"2_3.mp4")
//        );
//        return new Video("1","面试专题并发篇", LocalDateTime.now(),"2.png","祝你面试游刃有余",
//                List.of("面试","Java","计算机技术"),plays,"自制","科技->计算机技术");
//
//    }
//控制器类调用service，service包含业务逻辑，service调用map对数据进行操作
