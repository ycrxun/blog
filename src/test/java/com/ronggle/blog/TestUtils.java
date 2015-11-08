package com.ronggle.blog;

import com.ronggle.blog.utils.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by soi on 15-11-8.
 */
public class TestUtils {

    @Test
    public void testTimestamp() throws ParseException {
        long t = Calendar.getInstance().getTimeInMillis();
        System.out.println(t);
        Integer i = Integer.parseInt(new SimpleDateFormat("YYYYMMdd").format(new Date()));
        System.out.println(i);
        System.out.println(DateUtil.getCurDate().getTime());
        long l = new Date().getTime() / 1000L;
        System.out.println(l);
    }
}
