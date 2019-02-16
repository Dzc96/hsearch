package com.gdei.hsearch.service.user;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.gdei.hsearch.entity.Role;
import com.gdei.hsearch.entity.User;
import com.gdei.hsearch.repository.RoleRepository;
import com.gdei.hsearch.repository.UserRepository;
import com.gdei.hsearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //根据用户名查到用户，以及它对应的角色
    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) return null;

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty())
            throw new DisabledException("权限非法");

        List<GrantedAuthority> authorities = new ArrayList<>();
        //把查到用户的对应角色，逐一添加到 List<GrantedAuthority>
        roles.forEach(role->authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName())));

        //设置用户的角色
        user.setAuthorityList(authorities);
        return user;

    }
}
