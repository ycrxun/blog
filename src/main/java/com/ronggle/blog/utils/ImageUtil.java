package com.ronggle.blog.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

public class ImageUtil {

    private ImageUtil() {
    }

    /**
     * resize image from file to save path
     *
     * @param x        x axis
     * @param y        y axis
     * @param w        width
     * @param h        height
     * @param filePath src file path
     * @param savePath dist file path
     */
    public static boolean resize(int x, int y, int w, int h, String filePath, String savePath) {
        try {
            //sourceRegion（int x,int ,y,int width,int height）在某位置切割图片
            Thumbnails.of(filePath).size(w, h).sourceRegion(x, y, w, h).toFile(savePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean resize(int w, int h, String filePath, String savePath) {
        return ImageUtil.resize(w, h, new File(filePath), new File(savePath));
    }

    public static boolean resize(int w, int h, File file, File save) {
        try {
            Thumbnails.of(file).size(w, h).toFile(save);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean forceSize(int w,int h,String src,String dest) {
        return forceSize(w,h,new File(src),new File(dest));
    }

    public static boolean forceSize(int w,int h,File src,File dest) {
        boolean b = false;
        try{
            Thumbnails.of(src).forceSize(w,h).toFile(dest);
            b= true;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}