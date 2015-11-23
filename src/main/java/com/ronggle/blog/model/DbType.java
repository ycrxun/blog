package com.ronggle.blog.model;

/**
 * Created by soi on 15-10-30.
 */
public enum  DbType {

    MySQL("mysql"),
    ORACLE("oracle");

    public final String TYPE;

    DbType(String type) {
        this.TYPE = type;
    }

}
