package com.sillykid.app.utils;

import com.sillykid.app.entity.CalendarControlDateBean;

import java.util.Comparator;

/**
 * 排序
 * Created by Admin on 2017/9/16.
 */

public class SequencingUtils implements Comparator<CalendarControlDateBean> {

    @Override
    public int compare(CalendarControlDateBean lhs, CalendarControlDateBean rhs) {
        // TODO Auto-generated method stub
        return (int) (lhs.getDateTime() - rhs.getDateTime());
    }

}