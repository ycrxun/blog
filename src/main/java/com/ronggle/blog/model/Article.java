package com.ronggle.blog.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * This is blog article model
 * Created by soi on 15-11-2.
 */
public class Article extends Model<Article> {

    public static final Article dao = new Article();
}
