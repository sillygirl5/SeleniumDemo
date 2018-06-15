package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * @author cdzhaojiajun
 */
public class DateUtil {

    public static String getDateString(String formatString, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(date.getTime());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date.getTime());
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentDate(String template) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(template);
        return format.format(date.getTime());
    }

    /**
     * 获取当天之后的时间
     *
     * @param format
     * @param days
     * @return
     */
    public static String getDateAfterToday(String format, int days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +days);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当天之前的时间
     *
     * @param format
     * @param days
     * @return
     */
    public static String getDateBeforeToday(String format, int days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    /**
     * 对比时间前后
     *
     * @param date1
     * @param date2
     * @return date1==data2 0, date1<date2 -1, date1>date2 1,
     */
    public static int compareDate(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_1 = sdf.parse(date1);
        System.out.println(date_1.toString());
        Date date_2 = sdf.parse(date2);
        System.out.println(date_2.toString());
        return date_1.compareTo(date_2);
    }

    public static void main(String[] args) {

        try {
            int code = DateUtil.compareDate("2018-10-3 10:50:20", "2018-10-3 00:00:00");
            System.out.println(code);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
