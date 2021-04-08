package com.zjh.notice;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        String dateString = "2021-4-7";
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        Date date = sdf.parse(dateString);
        System.out.println(date);
        System.out.println(date.getTime());
        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println(timestamp);
    }
}
