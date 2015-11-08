package com.ronggle.blog.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类,提供通用的日期操作方法<br>
 * 本类中涉及到的所有日期时间格式表达串均遵循jdk中时间表达标准,参见下表: <br>
 * <p>
 * <table border=0 cellspacing=3 cellpadding=2 style='font-size:10pt;border:1px solid #000' summary="Chart shows pattern letters, date/time component, presentation, and examples.">
 * <tr bgcolor="#ccccff">
 * <th align=left>字符表达式
 * <th align=left>日期或时间表达含义
 * <th align=left>数据类型
 * <th align=left>例子
 * <tr>
 * <td><code>G</code>
 * <td>纪元描述
 * <td>文本
 * <td><code>AD 公元</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>y</code>
 * <td>年份描述
 * <td>年份
 * <td><code>1996</code>; <code>96</code>
 * <tr>
 * <td><code>M</code>
 * <td>一年中的第几月
 * <td>月份
 * <td><code>July</code>; <code>Jul</code>; <code>07</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>w</code>
 * <td>一年中的第几周
 * <td>数值
 * <td><code>27</code>
 * <tr>
 * <td><code>W</code>
 * <td>一个月中的第几周
 * <td>数值
 * <td><code>2</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>D</code>
 * <td>一年中第几天
 * <td>数值
 * <td><code>189</code>
 * <tr>
 * <td><code>d</code>
 * <td>一月中的第几天
 * <td>数值
 * <td><code>10</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>F</code>
 * <td>周几
 * <td>数值
 * <td><code>2</code>
 * <tr>
 * <td><code>E</code>
 * <td>周几
 * <td>文本
 * <td><code>Tuesday</code>; <code>Tue</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>a</code>
 * <td>Am/pm 标识
 * <td>文本
 * <td><code>PM 下午</code>
 * <tr>
 * <td><code>H</code>
 * <td>一天中的小时 (0-23)
 * <td>数值
 * <td><code>0</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>k</code>
 * <td>一天中的小时(1-24)
 * <td>数值
 * <td><code>24</code>
 * <tr>
 * <td><code>K</code>
 * <td>一天中的小时 am/pm (0-11)
 * <td>数值
 * <td><code>0</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>h</code>
 * <td>一天中的小时 am/pm (1-12)
 * <td>数值
 * <td><code>12</code>
 * <tr>
 * <td><code>m</code>
 * <td>分钟
 * <td>数值
 * <td><code>30</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>s</code>
 * <td>秒数
 * <td>数值
 * <td><code>55</code>
 * <tr>
 * <td><code>S</code>
 * <td>毫秒
 * <td>数值
 * <td><code>978</code>
 * <tr bgcolor="#eeeeff">
 * <td><code>z</code>
 * <td>Time zone
 * <td>通用时区
 * <td><code>Pacific Standard Time</code>; <code>PST</code>;
 * <code>GMT-08:00</code>
 * <tr>
 * <td><code>Z</code>
 * <td>Time zone
 * <td>RFC 822标准时区
 * <td><code>-0800</code>
 * </table>
 *
 * @author lh
 */
public final class DateUtil {

    /**
     * dateDiff()方法的unit参数,以年为单位
     */
    public final static byte DIFF_YEAR = 0;

    /**
     * dateDiff()方法的unit参数,以月为单位
     */
    public final static byte DIFF_MONTH = 1;

    /**
     * dateDiff()方法的unit参数,以日为单位
     */
    public final static byte DIFF_DAY = 2;

    /**
     * dateDiff()方法的unit参数,以小时为单位
     */
    public final static byte DIFF_HOUR = 3;

    /**
     * dateDiff()方法的unit参数,以分钟为单位
     */
    public final static byte DIFF_MINUTE = 4;

    /**
     * dateDiff()方法的unit参数,以秒为单位
     */
    public final static byte DIFF_SECONDE = 5;

    /**
     * dateDiff()方法的unit参数,以毫秒为单位
     */
    public final static byte DIFF_MILLISECOND = 6;

    /**
     * 取得当前日期对象
     *
     * @return 返回java.util.Date日期对象
     */
    public static Date getCurDate() {
        return getCurCalendar().getTime();
    }

    /**
     * 获取当前时间日历对象
     *
     * @return 返回java.util.Calendar日期对象
     */
    public static Calendar getCurCalendar() {
        return Calendar.getInstance();
    }

    /**
     * 取得当前时间,格式为HH:MM:SS
     *
     * @return 返回当前时间
     */
    public static String getCurTime() {
        return getDate(getCurDate(), "HH:mm:ss");
    }

    /**
     * 取得当前日期的字符串表示,格式为 yyyy-MM-dd
     *
     * @return 返回日期的字符串表示
     */
    public static String getDate() {
        return getDate(getCurDate(), "yyyy-MM-dd");
    }

    public static String getDate(Date date) {
        return getDate(date, "yyyy-MM-dd");
    }

    public static String getDate(String format) {
        return getDate(getCurDate(), format);
    }

    /**
     * 获取当前日期时间字符串,格式为 yyyy-MM-dd hh:mm:ss
     *
     * @return 返回当前字符串
     */
    public static String getDatetime() {
        return getDate(getCurDate(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将指定Date类型转换成指定格式的字符串,格式串参见类注释
     *
     * @param date   日期方式
     * @param format 指定的格式,当format为NULL或空串时,<BR>
     *               默认为 yyyy-MM-dd 格式
     * @return 当date为NULL时, 返回空串
     */
    public static String getDate(Date date, String format) {

        String dtstr = "";
        if (date == null) {
            return dtstr;
        }

        if (format == null || "".equals(format.trim())) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        dtstr = sdf.format(date);
        return (dtstr == null ? "" : dtstr);

    }

    /**
     * 取得标准格式的日期: HH:mm:ss
     *
     * @param c 日期对象
     * @return 返回指定时间的小时:分钟:秒数
     */
    public static String getTime(Calendar c) {
        return getDate(c.getTime(), "HH:mm:ss");
    }

    /**
     * 取得当前年份
     *
     * @return 四位年份 yyyy
     */
    public static String getYear() {
        Calendar c = getCurCalendar();
        return "" + c.get(Calendar.YEAR);
    }

    /**
     * 取得当前月份
     *
     * @return
     */
    public static String getMonth() {
        Calendar c = getCurCalendar();
        return "" + (c.get(Calendar.MONTH) + 1);
    }

    /**
     * 取得当前日
     *
     * @return
     */
    public static String getDay() {
        Calendar c = getCurCalendar();
        return "" + (c.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 取得当前星期数 1,2,3,4,5,6,7 代表 星期一.....
     *
     * @return
     */
    public static String getWeek() {
        Calendar c = getCurCalendar();
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week > 1) {
            week--;
        } else {
            week = 7;
        }
        return "" + week;
    }

    /**
     * 将指定字串转换按指定格式转换成java.util.Date类型
     *
     * @param dateStr 日期字串
     * @param format  指定的格式,当format为NULL或空串时,<BR>
     *                默认为 yyyy-MM-dd 格式
     * @return 当dateStr 为null或者转换出错时,返回NULL值
     * @throws RuntimeException 日期格式与格式串不一致时，抛出RuntimeException
     */
    public static Date parseDate(String dateStr, String format) {
        Date date = null;

        if (format == null || "".equals(format.trim())) {
            format = "yyyy-MM-dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException ex) {
            throw new RuntimeException("日期格式与格式串不一致", ex);
        }

        return date;
    }

    /**
     * 将指定字串转换按指定格式转换成java.util.Date类型
     *
     * @param dateStr 日期字串 yyyy-MM-dd 格式
     * @return 当dateStr 为null或者转换出错时,返回NULL值
     * @throws RuntimeException 日期格式与格式串不一致时，抛出RuntimeException
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * 将指定字串转换按指定格式转换成java.util.Date类型
     *
     * @param dateStr 日期字串 yyyy-MM-dd HH:mm:ss 格式
     * @return 当dateStr 为null或者转换出错时,返回NULL值
     * @throws RuntimeException 日期格式与格式串不一致时，抛出RuntimeException
     */
    public static Date paseDatetime(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将java.util.Date转换成 java.sql.Date类型
     *
     * @param date java.util.Date对象
     * @return java.sql.Date对象, 如果date为NULL, 则返回NULL值
     */
    public static java.sql.Date parseSQLDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将java.util.Date转换成 java.sql.Timestamp类型
     *
     * @param date java.util.Date对象
     * @return ava.sql.Timestamp对象, 如果date为NULL, 则返回NULL值
     */
    public static Timestamp parseTimestamp(Date date) {

        if (date == null) {
            return null;
        }
        return new Timestamp(date.getTime());

    }

    /**
     * get long unix timestamp
     *
     * @return long unix timestamp
     */
    public static long getUnixTimestamp() {
        return new Date().getTime() / 1000L;
    }

    /**
     * 得到由c指定的日期所在月的开始日期
     *
     * @param c
     * @return
     */
    public static Calendar getMonthBegin(Calendar c) {
        Calendar r = getCurCalendar();
        r.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
        return (r);
    }

    /**
     * 得到由c指定的日期所在月的结束日期
     *
     * @param c
     * @return
     */
    public static Calendar getMonthEnd(Calendar c) {
        Calendar r = getCurCalendar();
        r.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, 1, 23, 59, 59);
        r.add(Calendar.DATE, -1);
        return (r);
    }

    /**
     * 将指定时间串转换成日期时间对象
     *
     * @param dateStr 时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
     * @return 返回指定时间的Calendar对象
     * @throws NullPointerException 当日期时间格式不正确时
     */
    public static Calendar parseCalendar(String dateStr) {
        Date dt = null;
        dt = parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
        if (dt == null) {
            dt = parseDate(dateStr);
        }
        Calendar c = getCurCalendar();
        c.setTime(dt);
        return (c);
    }

    /**
     * @return 相差时差
     * @see #dateDiff(Calendar, Calendar, byte)
     */
    public static long dateDiff(String dateFrom, String dateTo, byte unit) {
        Calendar from = parseCalendar(dateFrom);
        Calendar to = parseCalendar(dateTo);
        return dateDiff(from, to, unit);
    }

    /**
     * @param dateFrom 开始时间
     * @param dateTo   结束时间
     * @param unit     单位
     * @return
     * @see #dateDiff(Calendar, Calendar, byte)
     */
    public static long dateDiff(Date dateFrom, Date dateTo, byte unit) {
        Calendar from = Calendar.getInstance();
        from.setTime(dateFrom);
        Calendar to = Calendar.getInstance();
        to.setTime(dateTo);
        return dateDiff(from, to, unit);
    }

    /**
     * 返回2个日期之间的差距 unit是时间计算的单位,为本类常量DIFF_&lgt;XXXX&rgt;中的任一类型
     *
     * @param dateFrom 开始时间的时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
     * @param dateTo   结束时间的时间串,可以是yyyy-MM-dd格式 或者 yyyy-MM-dd HH:mm:ss 格式
     * @param unit     是时间计算的单位,为以下值中的任一值:<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_YEAR &nbsp;以年为单位&nbsp;<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MONTH &nbsp;以月为单位&nbsp;<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_DAY &nbsp;以日为单位&nbsp;<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_HOUR &nbsp;以小时为单位&nbsp;<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MINUTE &nbsp;以分钟为单位&nbsp;<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_SECONDE &nbsp;以秒为单位&nbsp;<br>
     *                 &nbsp;&nbsp;&nbsp;&nbsp;DIFF_MILLISECOND &nbsp;以毫秒为单位&nbsp;
     * @return 相差时差
     */
    public static long dateDiff(Calendar dateFrom, Calendar dateTo, byte unit) {
        long diff = dateTo.getTimeInMillis() - dateFrom.getTimeInMillis();
        long interval = 0;
        switch (unit) {
            case 0: {
                Calendar from = dateFrom;
                Calendar to = dateTo;
                interval = to.get(Calendar.YEAR) - from.get(Calendar.YEAR);
                from.set(Calendar.YEAR, to.get(Calendar.YEAR));
                if (from.after(to)) {
                    if (interval < 0) {
                        interval++;
                    } else {
                        interval--;
                    }
                }
                break;
            }
            case 1: {
                int year = dateTo.get(Calendar.YEAR) - dateFrom.get(Calendar.YEAR);
                int month = dateTo.get(Calendar.MONTH)
                        - dateFrom.get(Calendar.MONTH);
                Calendar from = dateFrom;
                Calendar to = dateTo;
                from.set(Calendar.YEAR, dateTo.get(Calendar.YEAR));
                from.set(Calendar.MONTH, dateTo.get(Calendar.MONTH));
                interval = year * 12 + month;
                if (from.after(to)) {
                    if (interval < 0) {
                        interval++;
                    } else {
                        interval--;
                    }
                }
                break;
            }
            case 2:
                interval = (int) (diff / (1000 * 60 * 60 * 24));
                break;

            case 3:
                interval = (int) (diff / (1000 * 60 * 60));
                break;

            case 4:
                interval = (int) (diff / (1000 * 60));
                break;

            case 5:
                interval = (int) (diff / 1000);
                break;

            default:
                interval = diff;
        }
        return interval;
    }

    /**
     * 自由串格式化日期串,对于下列表中的字符支持\转义<br>
     * 例如:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;\yyyy 结果为 y08y (原因为第一个被转义,剩下的字串只能构造成两位年份)<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;\y 结果为 y <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;\yyyyy 结果为 y2008 <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;\yyyyyy 结果为 y2008y <br>
     * 其它字符如此类同
     *
     * @param date      日期对象
     * @param strFormat 格式串,含义如下,注意大小写区分:<br>
     *                  <p>
     *                  <table border=0 cellspacing=3 cellpadding=2 style='font-size:10pt;border:1px solid #000' summary="Chart shows pattern letters, date/time component, presentation, and examples.">
     *                  <tr bgcolor="#ccccff">
     *                  <th align=left>字符表达式
     *                  <th align=left>日期或时间表达含义
     *                  <th align=left>例子
     *                  <tr>
     *                  <td><code>yyyy</code>
     *                  <td>四位年
     *                  <td><code>2000, 2009</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>yy</code>
     *                  <td>两位年
     *                  <td><code>00, 09</code>
     *                  <tr>
     *                  <td><code>MM</code>
     *                  <td>两位月
     *                  <td><code>07 , 23</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>M</code>
     *                  <td>普通月
     *                  <td><code>7 , 23</code>
     *                  <tr>
     *                  <td><code>dd</code>
     *                  <td>两位天
     *                  <td><code>03, 12</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>d</code>
     *                  <td>普通天
     *                  <td><code>3, 12</code>
     *                  <tr>
     *                  <td><code>hh</code>
     *                  <td>两位小时
     *                  <td><code>03, 12</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>h</code>
     *                  <td>普通小时
     *                  <td><code> 3, 12</code>
     *                  <tr>
     *                  <td><code>mm</code>
     *                  <td>分
     *                  <td><code>03 , 12</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>m</code>
     *                  <td>分
     *                  <td><code>3 , 12</code>
     *                  <tr>
     *                  <td><code>ss</code>
     *                  <td>秒
     *                  <td><code>03 , 12</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>s</code>
     *                  <td>秒
     *                  <td><code>3 , 12</code>
     *                  <tr>
     *                  <td><code>SSS</code>
     *                  <td>三位微秒
     *                  <td><code>003, 012 , 199</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>S</code>
     *                  <td>微秒
     *                  <td><code>3, 12 , 199</code>
     *                  <tr>
     *                  <td><code>F</code>
     *                  <td>周几 ,数值
     *                  <td><code>3</code>
     *                  <tr bgcolor="#eeeeff">
     *                  <td><code>E</code>
     *                  <td>周几 ,文本
     *                  <td><code>星期三, Tuesday</code>
     *                  <tr>
     *                  <td><code>a</code>
     *                  <td>Am/pm 标识
     *                  <td><code> PM, 下午</code>
     *                  </table>
     * @return 如果date 或者 strFormat 为null,则返回空串，否则返回指定格式串
     * @since 1.0.1
     */
    public static String formartRandomDate(Date date, String strFormat) {
        if (date == null || strFormat == null) {
            return "";
        }

        String key = strFormat;
        key = key.replaceAll("(?<!\\\\)yyyy", getDate(date, "yyyy"));
        key = key.replaceAll("(?<!\\\\)yy", getDate(date, "yy"));
        key = key.replaceAll("\\\\y", "y");

        key = key.replaceAll("(?<!\\\\)MM", getDate(date, "MM"));
        key = key.replaceAll("(?<!\\\\)M", getDate(date, "M"));
        key = key.replaceAll("(?<!\\\\)mm", getDate(date, "mm"));
        key = key.replaceAll("(?<!\\\\)m", getDate(date, "m"));
        key = key.replaceAll("\\\\(M|m)", "$1");

        key = key.replaceAll("(?<!\\\\)dd", getDate(date, "dd"));
        key = key.replaceAll("(?<!\\\\)d", getDate(date, "d"));
        key = key.replaceAll("\\\\d", "d");

        key = key.replaceAll("(?<!\\\\)hh", getDate(date, "hh"));
        key = key.replaceAll("(?<!\\\\)h", getDate(date, "h"));
        key = key.replaceAll("(?<!\\\\)HH", getDate(date, "HH"));
        key = key.replaceAll("(?<!\\\\)H", getDate(date, "H"));
        key = key.replaceAll("\\\\(H|h)", "$1");

        key = key.replaceAll("(?<!\\\\)ss", getDate(date, "ss"));
        key = key.replaceAll("(?<!\\\\)s", getDate(date, "s"));
        key = key.replaceAll("(?<!\\\\)SSS", getDate(date, "SSS"));
        key = key.replaceAll("(?<!\\\\)SS", getDate(date, "SS"));
        key = key.replaceAll("(?<!\\\\)s", getDate(date, "S"));
        key = key.replaceAll("\\\\(S|s)", "$1");

        key = key.replaceAll("(?<!\\\\)F", getDate(date, "F"));
        key = key.replaceAll("\\\\F", "F");
        key = key.replaceAll("(?<!\\\\)E", getDate(date, "E"));
        key = key.replaceAll("\\\\E", "E");
        key = key.replaceAll("(?<!\\\\)a", getDate(date, "a"));
        key = key.replaceAll("\\\\a", "a");

        return key;
    }

    public static Date getBeforeDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return c.getTime();
    }

    public static Date getNextDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        return c.getTime();
    }

    public static String getNextDateStr(String dateStr) {
        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.parseDate(dateStr));
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        return DateUtil.getDate(c.getTime());
    }

    public static String getBeforDateStr(String dateStr) {
        Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.parseDate(dateStr));
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return DateUtil.getDate(c.getTime());
    }

    public static int getHour() {
        return getHour(getCurDate());
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    @SuppressWarnings("deprecation")
    public static int getMonth(Long millisecondTime) {
        Date date = new Date(millisecondTime);
        int month = date.getMonth() + 1;
        return month;

    }

    // 获取上个月结束
    public static String getBmonthEnd() {

        Calendar c = getCurCalendar();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return formartRandomDate(c.getTime(), "yyyy-MM-dd");
    }

    // 获取上个月起始
    public static String getBmonthStart() {

        Calendar c = getCurCalendar();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);

        return formartRandomDate(c.getTime(), "yyyy-MM-dd");
    }

    // 获取当月起始
    public static String getMothStart() {
        Calendar c = getCurCalendar();
        c.set(Calendar.DAY_OF_MONTH, 1);

        return formartRandomDate(c.getTime(), "yyyy-MM-dd");
    }

    /**
     * 取得7天前的日期
     */
    public static String get7DayBeforDate() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 7);
        return getDate(now.getTime(), "yyyy-MM-dd");
    }

    /**
     * 昨天日期
     */
    public static String getYesterday() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 1);
        return getDate(now.getTime(), "yyyy-MM-dd");
    }
}