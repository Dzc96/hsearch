package com.gdei.hsearch.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云服务  文件或文件流上传  删除
 */
public interface IQiNiuService {
    Response upLoadFile(File file) throws IOException;

    Response upLoadFile(InputStream inputStream) throws IOException;

    Response delete (String key) throws QiniuException;

}
