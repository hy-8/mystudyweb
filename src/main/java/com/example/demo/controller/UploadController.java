package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Controller
public class UploadController {
    @Value("${video-path}")
    private String videoPath;

    //处理上传文件
    @RequestMapping("/upload")
    @ResponseBody
    //MultipartFile 专用于上传二进制数据的类型,url是文件名
    public Map<String, String> upload(int i, int chunks, MultipartFile data, String url) throws IOException {
//        System.out.println(i+" "+chunks+" "+url);
        data.transferTo(Path.of(videoPath,url+".part"+i ));//把上传的临时文件传输到目录下
        return Map.of(url,i*100.0/chunks+"%");
    }

    @RequestMapping("/finish")
    @ResponseBody
    public void finish(int chunks,String url) throws FileNotFoundException {


        //将多个分块文件合成一个文件
        //FileOutputStream 文件输出流，创建新文件并写入内容
        try( FileOutputStream os=new FileOutputStream(videoPath+url)){
            //写入内容
            for(int i=1;i<=chunks;i++)
            {
                Path part= Path.of(videoPath,url+".part"+i);
                //参数一：原始文件，参数二：目标文件
                Files.copy(part,os);
                part.toFile().delete();//删除part文件
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //finally{os.close()}java自己帮你做
    }
    @RequestMapping("/uploadCover")
    @ResponseBody
    public Map<String,String> uploadCover(MultipartFile data,String cover) throws IOException {
        data.transferTo(Path.of("d:\\img",cover));
        return Map.of("cover",cover);
    }
}
