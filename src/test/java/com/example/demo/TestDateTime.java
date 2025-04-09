package com.example.demo;

import java.time.LocalDateTime;
import java.time.LocalTime;

/*  LocalDateTime - 年月日时分秒
*   LocalTime - 时分秒
*   LocalDate - 年月日
*
* */
public class TestDateTime {
    public static void main(String[] args) {
        LocalDateTime dt1 = LocalDateTime.of(2025,4,5,16,32,34);

        LocalTime dt2=LocalTime.of(16,32,34);
        //1.计算  会自动进位，4.5 加 25天 变成4.30； 加26天 变成5.1
        System.out.println(dt1.plusDays(25));
        System.out.println(dt1.plusDays(26));
        System.out.println(dt1.minusDays(5));

        //2.获取当前时间
        System.out.println(LocalDateTime.now());

        //3.把字符串转成LocalDateTime类型
        String n="2025-05-01T16:32:34";   //格式有要求
        LocalDateTime dt3=LocalDateTime.parse(n);
        System.out.println(dt3.getYear());
        System.out.println(dt3.getMonth());
        System.out.println(dt3.getMonthValue());
        System.out.println(dt3.getDayOfMonth());
        System.out.println(dt3.getDayOfWeek());
        System.out.println(dt3.getDayOfYear());
    }
}
