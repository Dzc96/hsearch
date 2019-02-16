package com.gdei.hsearch.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于角色的登陆入口控制
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {


    private PathMatcher pathMatcher = new AntPathMatcher();
    //用Map保存路径和对应登陆页面的映射关系
    private final Map<String, String> authEntryPointMap;

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authEntryPointMap = new HashMap<>();
        authEntryPointMap.put("/user/**", "/user/login");
        authEntryPointMap.put("/admin/**", "/admin/login");
    }

    /**
     * 根据请求跳转到指定登陆页面
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {

        //遍历之前保存的路径和对应登陆页面映射关系的Map
        //匹配时就跳转到对应登陆页面
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        for (Map.Entry<String, String> authEntry : this.authEntryPointMap.entrySet()) {
                if (this.pathMatcher.match(authEntry.getKey(), uri))
                    return authEntry.getValue();
        }
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
