package com.gdei.hsearch.repository;

import com.gdei.hsearch.entity.SupportAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupportAddressRepository extends CrudRepository<SupportAddress, Long> {

    /**
     * 返回对应行政级别下的所有城市/区域
     * @param level
     * @return
     */
    List<SupportAddress> findAllByLevel(String level);


}
