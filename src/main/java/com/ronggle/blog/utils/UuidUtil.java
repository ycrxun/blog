package com.ronggle.blog.utils;

import java.util.UUID;

/**
 * Created by soi on 15-11-6.
 */
public final class UuidUtil {

    private UuidUtil(){}

    public synchronized static String  getUuid32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public synchronized static String  getUuid36(){
        return UUID.randomUUID().toString();
    }

}
