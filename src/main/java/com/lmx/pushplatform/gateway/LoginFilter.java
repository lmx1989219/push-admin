package com.lmx.pushplatform.gateway;

import com.lmx.pushplatform.gateway.dao.DeveloperRep;
import com.lmx.pushplatform.gateway.entity.DeveloperEntity;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.util.NumberUtils;

import javax.servlet.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2018/11/24.
 */
public class LoginFilter implements Filter {

    private DeveloperRep developerRep;

    public LoginFilter(DeveloperRep developerRep) {
        this.developerRep = developerRep;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userId = httpServletRequest.getHeader("token");
        if (userId != null) {
            DeveloperEntity developerEntity = developerRep.getOne(Long.parseLong(userId));
            if (developerEntity == null) {
                try (OutputStream os = response.getOutputStream();) {
                    os.write("{\"code\":\"9995\",\"message\":\"您还未登录呢，请操作一下吧\"}".getBytes());
                    os.flush();
                    return;
                } catch (Exception e) {
                    throw e;
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
