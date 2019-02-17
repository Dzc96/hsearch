package com.gdei.hsearch.service.house;

import com.gdei.hsearch.service.ServiceResult;
import com.gdei.hsearch.web.dto.HouseDTO;
import com.gdei.hsearch.web.form.HouseForm;

/**
 * 房屋管理服务接口
 */
public interface IHouseService {

    ServiceResult<HouseDTO> save(HouseForm houseForm);


}
