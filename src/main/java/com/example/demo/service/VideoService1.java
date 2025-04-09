package com.example.demo.service;

import com.example.demo.dto.Play;
import com.example.demo.dto.Video;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/*
* 从文件中获取视频数据
* */
@Service
public class VideoService1 {
    public VideoService1(){

    }

 //   @PostConstruct  //这是一个初始化方法，在对象创建之后只会调用一次。
    public void init(){ //初始化
        try {
            List<String> data = Files.readAllLines(Path.of("data","p.csv"));
            for (String line : data) {
                String[] s=line.split(",");
                    String[] tags=s[7].split("_");
                    Video video= new Video(s[0],s[3], LocalDateTime.parse(s[6]),s[4],s[5], Arrays.asList(tags),getPlayList(s[0]),s[1],s[2]);
                    map.put(s[0],video);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //使用map类型作为方法直接获取值的桥梁，并且可以快速访问
    Map<String,Video> map=new HashMap<>();

    // 查询方法：  根据视频编号，查询video  对象
    public Video find(String bv) {
//        try {
//            List<String> data = Files.readAllLines(Path.of("data","p.csv"));
//
//            //读取文件中的每一行数据
//            for (String line : data) {
//                String[] s=line.split(",");
//                if(s[0].equals(bv)){
//                    String[] tags=s[7].split("_");
//                    return new Video(s[0],s[3], LocalDateTime.parse(s[6]),s[4],s[5], Arrays.asList(tags),getPlayList(bv),s[1],s[2]);
//                }
//            }
//            //没找到
//            return null;
//        } catch (IOException e) {
//            //RuntimeException是运行时异常，将编译时异常转换为运行时异常，这样就可以不用throws上级
//            throw new RuntimeException(e);
//        }
        return map.get(bv);
    }
    //读取video数据
    private static List<Play> getPlayList(String bv){
        try {
            List<String> vdata=Files.readAllLines(Path.of("data","v_"+ bv +".csv"));
            List<Play> list=new ArrayList<>();
            for (String vline : vdata) {
                String[] ss=vline.split(",");
                list.add(new Play(ss[0],ss[1], LocalTime.parse(ss[3]),ss[2])) ;
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
