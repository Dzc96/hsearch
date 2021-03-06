package com.gdei.hsearch.web.controller.admin;

import com.gdei.hsearch.base.ApiResponse;
import com.gdei.hsearch.service.house.IQiNiuService;
import com.gdei.hsearch.web.dto.QiNiuPutRet;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class AdminController {
    @Autowired
    private IQiNiuService qiNiuService;

    @Autowired
    private Gson gson;

    @GetMapping("/admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcomePage(){
        return "admin/welcome";
    }


    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin/login";
    }

    /**
     * 房源列表页
     * @return
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }


    /**
     * 新增房源功能页
     * @return
     */
    @GetMapping("admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }


    /**
     * 上传图片到七牛云
     * @param file
     * @return
     */
    @PostMapping(value = "admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }

        String fileName = file.getOriginalFilename();

        try {
            InputStream inputStream = file.getInputStream();
            Response response =qiNiuService.upLoadFile(inputStream);
            if (response.isOK()) { //如果七牛云上传文件成功，则获得封装后的返回值传给前台
                //七牛返回的是json数据，封装为对象后再传给前台解析
                QiNiuPutRet ret = gson.fromJson(response.bodyString(), QiNiuPutRet.class);
                return ApiResponse.ofSuccess(ret);
            } else {
                return ApiResponse.ofMessage(response.statusCode, response.getInfo());
            }
        } catch (QiniuException e) {
           Response response =  e.response;
           try {
               return ApiResponse.ofMessage(response.statusCode, response.bodyString());
           } catch (QiniuException e1) {
               e1.printStackTrace();
               return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
           }
        }
        catch (IOException e) {
            return ApiResponse.ofStatus(ApiResponse.Status.INTERNAL_SERVER_ERROR);
        }

    }






}

