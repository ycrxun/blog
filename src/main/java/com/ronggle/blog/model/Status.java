package com.ronggle.blog.model;

/**
 * database table status enum<br/>
 * disable-0<br/>
 * enable-1<br/>
 * Created by soi on 15-11-15.
 */
public enum Status {

    DISABLE(0),
    ENABLE(1);

    public final Integer status;

    Status(final Integer status) {
        this.status = status;
    }
}
