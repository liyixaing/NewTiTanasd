package com.lxh.baselibray.util;

/**
 * Created by chenxi on 2019/4/17.
 */

public class StringUtils {

    public static  String[] spiltStringBy(String content,String sub) {

        String[] strings = content.split(sub);
        return strings;
    }

}
