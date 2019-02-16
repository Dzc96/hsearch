package com.gdei.hsearch.service;

import com.gdei.hsearch.entity.User;
import org.springframework.stereotype.Service;



public interface IUserService {
    User findUserByName(String userName);
}
