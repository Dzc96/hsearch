package com.gdei.hsearch.service.house;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class IQiNiuServiceImpl implements IQiNiuService, InitializingBean {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.Bucket}")
    private String bucket;

    //七牛上传文件后的返回值
    private StringMap putPolicy;


    /**
     * 以File的形式上传文件到七牛云  最多重试上传3次
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public Response upLoadFile(File file) throws IOException {
        Response response = this.uploadManager.put(file, null, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        return response;
    }

    /**
     * 以文件流的形式上传文件到七牛云 最多重试上传3次
     * @param inputStream
     * @return
     * @throws Exception
     */
    @Override
    public Response upLoadFile(InputStream inputStream) throws IOException {
        Response response = this.uploadManager.put(inputStream, null, getUploadToken(),null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
          response = this.uploadManager.put(inputStream, null, getUploadToken(),null, null);
          retry++;
        }
        return response;
    }


    /**
     * 从七牛云上删除文件 只需要传入一个key
     * @param key
     * @return
     * @throws QiniuException
     */
    @Override
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(this.bucket, key);
        int retry = 3;
        while (response.needRetry() && retry < 3) {
            response = bucketManager.delete(this.bucket, key);
        }
        return response;
    }

    //在Bean初始化后之后会调用这个方法，初始化文件上传到七牛后的返回值
    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }


    /**
     * 获取上传凭证
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}
