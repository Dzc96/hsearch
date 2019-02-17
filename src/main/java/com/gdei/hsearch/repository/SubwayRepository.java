package com.gdei.hsearch.repository;

import com.gdei.hsearch.entity.Subway;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubwayRepository extends CrudRepository<Subway, Long> {
    List<Subway> findAllByCityEnName(String cityEnName);
}
