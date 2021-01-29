package com.example.software.project.development.management.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hanhuafeng
 * @version V1.0
 * @description
 * @date 2021/1/27
 */
public class DateUtils {
    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 在返回true，不在返回false
     * @author hfhan
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * 判断时间是否在时间段内 开始时间大于结束时间返回true
     *
     * @param beginTime
     * @param endTime
     * @return true
     * Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
     * Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
     * 如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，请注意。
     */
    public static boolean belongCalendar(Date beginTime, Date endTime) {

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (begin.after(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 当前月第一天
     *
     * @return 当前月第一天
     */
    public static Date getThisMonth() {
        // 获取前月的第一天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        // 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 当年第一天
     *
     * @return 天
     * @throws Exception 异常
     */
    public static Date getThisYear() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
        Date time = new Date();
        String time1 = format3.format(time);
        return format.parse(time1 + "-01-01");
    }
}
