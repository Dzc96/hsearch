package com.gdei.hsearch.service.house;

import com.gdei.hsearch.service.ServiceMultiResult;
import com.gdei.hsearch.web.dto.SupportAddressDTO;

/**
 * 地址服务
 */
public interface IAddressService {

    /**
     * 返回所有支持的城市列表
     * @return
     */
    ServiceMultiResult<SupportAddressDTO> findAllCities();

}
