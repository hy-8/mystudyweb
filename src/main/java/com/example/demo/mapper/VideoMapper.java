package com.example.demo.mapper;

import com.example.demo.dto.Video;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VideoMapper {
    //根据bv号查询视频
    //video的有参构造要和select语句的参数顺序一样
    //所以建议加一个无参构造，他会使用get，set方法去找
    @Select("""
        select
        bv,
        type,
        category,
        title,
        cover,
        introduction,
        publish_time as publishTime, 
        tags
    from video where bv=#{bv}
    """)
    Video findByBv(String bv);
    /*
    * 数据库习惯：underscore下划线分割多个单词
    * java习惯： 驼峰命名法：多个单词第二个开始用大写开头camel case
    * 导致名称不对应，导致查找不到
    *可以在application.properties中修改
    * Java面试_求职_计算机技术_面试技巧,在数据库里就是字符串
    * java里是list集合，我们在javabean里面修改格式输出对应的读个list
    * */

    //#{}里面的值名称和javabean对应的名称一样
    @Insert("""
            insert into video(type, category, title, cover, introduction, publish_time, tags) 
            values(#{type},#{category},#{title},#{cover},
            #{introduction},#{publishTime},#{tags})
            """)
    void insert(Video video);

    @Select("""
            select last_insert_id();
            """)//获取最近生成的自增主键值
    int lastInsertId();

    // 更新 bv 号
    @Update("update video set bv=#{bv} where id=#{id}")
    void updateBv(@Param("bv") String bv, @Param("id") int id);

}
