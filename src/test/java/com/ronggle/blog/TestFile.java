package com.ronggle.blog;

import com.ronggle.blog.utils.*;
import org.junit.Test;

/**
 * Created by soi on 15-11-18.
 */
public class TestFile {

    /**
     * get file real name(System.currentTimeMillis() + ext name)
     *
     * @param fileName file name
     * @return real name
     */
    private String getRealName(String fileName) {
        return System.currentTimeMillis() + getExtName(fileName);
    }

    /**
     * get file ext name
     *
     * @param fileName file name
     * @return ext name
     */
    private String getExtName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Test
    public void testGetExt() {
        String fileName = "xn.png";
        System.out.println(getExtName(fileName));
    }

    @Test
    public void testRealName(){
        String fileName = "哈哈.jpg";
        System.out.println(getRealName(fileName));
    }

    @Test
    public void testFileName(){
        String fileName = "哈哈.jpg";
        System.out.println(FileUtil.getFileName(fileName));
    }
    
    @Test
    public void testResize(){
        //ImageUtil.resize(20,20,300,300,"/home/soi/012133dsa4toxud8snsbo1.jpg","/home/soi/012.jpg");
    }

    @Test
    public void testForceSize(){
        ImageUtil.forceSize(360,270,"/home/soi/workspace/blog/src/main/webapp/assets/images/25.jpg","/home/soi/workspace/blog/src/main/webapp/assets/images/25.jpg");
    }

}
