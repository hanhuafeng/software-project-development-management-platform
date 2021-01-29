package com.example.software.project.development.management.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/27
 */
public class IdCardUtils {
    /**
     * 根据身份证号判断性别
     *
     * @param idNumber
     * @return
     */
    public static String judgeGender(String idNumber) throws IllegalArgumentException {
        System.out.println(idNumber.length());
        if (idNumber.length() != 18 && idNumber.length() != 15) {
            throw new IllegalArgumentException("身份证号长度错误");
        }
        int gender = 0;
        if (idNumber.length() == 18) {
            //如果身份证号18位，取身份证号倒数第二位
            char c = idNumber.charAt(idNumber.length() - 2);
            gender = Integer.parseInt(String.valueOf(c));
        } else {
            //如果身份证号15位，取身份证号最后一位
            char c = idNumber.charAt(idNumber.length() - 1);
            gender = Integer.parseInt(String.valueOf(c));
        }
        System.out.println("gender = " + gender);
        if (gender % 2 == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的年龄
     *
     * @return
     */
    public static int countAge(String idNumber) {
        if (idNumber.length() != 18 && idNumber.length() != 15) {
            throw new IllegalArgumentException("身份证号长度错误");
        }
        String year;
        String yue;
        String day;
        if (idNumber.length() == 18) {
            year = idNumber.substring(6).substring(0, 4);// 得到年份
            yue = idNumber.substring(10).substring(0, 2);// 得到月份
            day = idNumber.substring(12).substring(0, 2);//得到日
        } else {
            year = "19" + idNumber.substring(6, 8);// 年份
            yue = idNumber.substring(8, 10);// 月份
            day = idNumber.substring(10, 12);//日
        }
        Date date = new Date();// 得到当前的系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fyear = format.format(date).substring(0, 4);// 当前年份
        String fyue = format.format(date).substring(5, 7);// 月份
        String fday = format.format(date).substring(8, 10);//
        int age = 0;
        if (Integer.parseInt(yue) == Integer.parseInt(fyue)) {//如果月份相同
            if (Integer.parseInt(day) <= Integer.parseInt(fday)) {//说明已经过了生日或者今天是生日
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            }
        } else {

            if (Integer.parseInt(yue) < Integer.parseInt(fyue)) {
                //如果当前月份大于出生月份
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            } else {
                //如果当前月份小于出生月份,说明生日还没过
                age = Integer.parseInt(fyear) - Integer.parseInt(year) - 1;
            }
        }
        System.out.println("age = " + age);
        return age;
    }

}
