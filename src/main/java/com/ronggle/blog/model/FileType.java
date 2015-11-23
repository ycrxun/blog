package com.ronggle.blog.model;

/**
 * Created by soi on 15-11-17.
 */
public enum FileType {

    AVATAR("avatar"),
    PROFILE("profile"),
    BANNER("banner");

    public final String type;

    FileType(String type) {
        this.type = type;
    }
}
