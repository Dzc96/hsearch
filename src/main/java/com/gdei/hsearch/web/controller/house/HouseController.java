package com.gdei.hsearch.web.controller.house;

import com.gdei.hsearch.base.ApiResponse;
import com.gdei.hsearch.service.ServiceMultiResult;
import com.gdei.hsearch.service.house.AddressServiceImpl;
import com.gdei.hsearch.service.house.IAddressService;
import com.gdei.hsearch.web.dto.SupportAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HouseController {
    @Autowired
    private IAddressService addressService;


    /**
     * 获取城市列表
     * @return
     */
    @GetMapping("address/support/cities")
    @ResponseBody
    public ApiResponse getSupportCities() {
        ServiceMultiResult<SupportAddressDTO> result = addressService.findAllCities();
        if (result.getResultSize() == 0)
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_FOUND);
        return ApiResponse.ofSuccess(result.getResult());
    }



}
