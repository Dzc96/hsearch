package com.gdei.hsearch.base;

import com.gdei.hsearch.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 操作登陆用户的工具类
 */
public class LoginUserUtil {

    /**
     * 根据SpringSecurity来获取登陆的用户对象
     * @return
     */
    public static User load() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof User)
            return (User) principal;

        return null;
    }

    public static Long getLoginUserId() {
        User user = load();
        if (user == null)
            return -1L;
        return user.getId();
    }


}
