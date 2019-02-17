package com.gdei.hsearch.service.house;

import com.gdei.hsearch.entity.SupportAddress;
import com.gdei.hsearch.repository.SupportAddressRepository;
import com.gdei.hsearch.service.ServiceMultiResult;
import com.gdei.hsearch.web.dto.SupportAddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService{
    @Autowired
    private SupportAddressRepository supportAddressRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * 返回所有支持的城市
     * @return
     */
    @Override
    public ServiceMultiResult<SupportAddressDTO> findAllCities() {
        List<SupportAddress> addresses = supportAddressRepository.findAllByLevel(SupportAddress.Level.CITY.getValue());
        List<SupportAddressDTO> addressDTOS = new ArrayList<>();
        for (SupportAddress supportAddress : addresses) {//把每个地址通过ModelMapper转换为地址DTO
            SupportAddressDTO target = modelMapper.map(supportAddress, SupportAddressDTO.class);
            addressDTOS.add(target);
        }

        return new ServiceMultiResult<>(addressDTOS.size(), addressDTOS);
    }
}
