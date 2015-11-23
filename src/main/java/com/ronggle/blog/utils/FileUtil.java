package com.ronggle.blog.utils;

import java.io.File;

/**
 * Created by soi on 15-11-18.
 */
public class FileUtil {

    public static final String DEFAULT_FILE_EXT = ".jpg";

    /**
     * get file real name(System.currentTimeMillis() + ext name)
     *
     * @param fileName file name
     * @return real name
     */
    public static String getRealName(String fileName) {
        return System.currentTimeMillis() + getExtName(fileName);
    }

    /**
     * get file ext name
     *
     * @param fileName file name
     * @return ext name
     */
    public static String getExtName(String fileName) {
        return null == fileName ? DEFAULT_FILE_EXT : fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * delete ext name
     *
     * @param fileName file name
     * @return file name
     */
    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * get path from date,for example:2015/11/18
     *
     * @return path
     */
    public static String getDatePath() {
        return DateUtil.getDate("yyyy/MM/dd");
    }


    /**
     * get file md5
     *
     * @param file file absolute path
     * @return md5 string
     */
    public static String getFileMd5(String file) {
        return FileUtil.getFileMd5(new File(file));
    }

    /**
     * get file md5
     *
     * @param file file
     * @return md5 string
     */
    public static String getFileMd5(File file) {
        return Md5Util.md5(file);
    }
}
