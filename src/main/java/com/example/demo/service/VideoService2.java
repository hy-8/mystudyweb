package com.example.demo.service;

import com.example.demo.dto.Play;
import com.example.demo.dto.Video;
import com.example.demo.mapper.PlayMapper;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.util.Bv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//从数据库获取视频数据
@Service
public class VideoService2 {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private PlayMapper playMapper;

    public Video find(String bv){
        Video video=videoMapper.findByBv(bv);
        if(video==null) return null;
        List<Play> playList=playMapper.findByBv(bv);
        video.setPlayList(playList);
        return video;
    }

    //发布视频
    public String publish(Video video){
        //设置发布时间
        video.setPublishTime(LocalDateTime.now());
        //1.向video表插入视频
        videoMapper.insert(video);
        //2.生成bv 号
        int id=videoMapper.lastInsertId();
        String bv= Bv.get(id);
        //3.更新bv号
        videoMapper.updateBv(bv,id);
        //4.向play表插入所有视频选集
        for (Play play : video.getPlayList()) {
            playMapper.insert(play,bv);
        }
        return bv;
    }
}
