package com.ronggle.blog.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;
import com.ronggle.blog.model.Dict;
import com.ronggle.blog.utils.FileUtil;
import com.ronggle.blog.utils.ImageUtil;

import java.io.File;

/**
 * Created by Administrator on 2015/10/30.
 */
public class BaseController extends Controller {

    protected Integer pageNow;
    protected Integer pageSize = Dict.PageSize;

    protected void renderJsonForIE(Object object) {
        render(new JsonRender(object).forIE());
    }

    protected void success() {
        success(100, "request successful");
    }

    protected void success(Object data) {
        success(100, "request successful", data);
    }

    protected void success(Integer code, String message) {
        success(new Record().set("code", code).set("message", message));
    }

    protected void success(Integer code, String message, Object data) {
        success(new Record().set("code", code).set("message", message).set("data", data));
    }

    protected void success(Record record) {
        renderJsonForIE(record);
    }

    protected void error() {
        error(101, "request bad");
    }

    protected void error(Object data) {
        error(101, "request bad", data);
    }

    protected void error(Integer code, String message) {
        error(new Record().set("code", code).set("message", message));
    }

    protected void error(Integer code, String message, Object data) {
        error(new Record().set("code", code).set("message", message).set("data", data));
    }

    protected void error(Record record) {
        renderJsonForIE(record);
    }

    /**
     * get upload file rename to folder
     *
     * @param paramName the file in form input name
     * @param folder    you need rename to the folder
     * @return record(<br/>
     * file_type:upload file type,<br/>
     * file_size:upload file size,<br/>
     * file_name:upload file name,<br/>
     * real_name:upload file real name on server,<br/>
     * absolute_path:upload file absolute path on server,<br/>
     * relative_path:upload file relative path on server
     *);
     */
    protected Record getUpload(String paramName, String folder) {
        /**
         * new map
         */
        Record record = null;
        /**
         * 2015/11/18
         */
        String dateFolder = FileUtil.getDatePath();
        /**
         * /temp/2015/11/18
         */
        String relativeFolder = File.separator + folder + File.separator + dateFolder;
        /**
         * ~/temp/2015/11/18
         */
        String absoluteFolder = PathKit.getWebRootPath() + relativeFolder;

        UploadFile uploadFile = getFile(paramName, absoluteFolder);

        if (null != uploadFile) {
            record = new Record();
            record.set("file_type", uploadFile.getContentType());

            File file = uploadFile.getFile();
            record.set("file_size", file.length());

            String fileName = uploadFile.getFileName();
            record.set("file_name", fileName);

            String realName = FileUtil.getRealName(fileName);
            record.set("real_name", realName);

            /**
             * ~/temp/2015/11/18/213123.png
             */
            String newFilePath = absoluteFolder + File.separator + realName;
            record.set("absolute_path", newFilePath);

            File newFile = new File(newFilePath);
            file.renameTo(newFile);

            /**
             *  /temp/2015/11/18/213123.png
             */
            String relativePath = relativeFolder + File.separator + realName;
            record.set("relative_path", relativePath);
        }
        return record;
    }

    /**
     * get upload file and thumbnail by width,height
     *
     * @param paramName the file in form input name
     * @param folder    you need rename to the folder
     * @param width     thumb width
     * @param height    thumb height
     * @return record(<br/>
     * file_type:upload file type,<br/>
     * file_size:upload file size,<br/>
     * file_name:upload file name,<br/>
     * real_name:upload file real name on server,<br/>
     * absolute_path:upload file absolute path on server,<br/>
     * relative_path:upload file relative path on server
     *);
     */
    protected Record getUpload(String paramName, String folder, int width, int height) {
        Record record = getUpload(paramName, folder);
        if (null != record) {
            String file = record.getStr("absolute_path");
            ImageUtil.forceSize(width, height, file, file);
        }
        return record;
    }
}
