package com.zjh.notice.kit;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 *
 * @author zongjunhao
 */
public class Utils {
    public static Timestamp strToTimestamp(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        return new Timestamp(date.getTime());
    }
}
