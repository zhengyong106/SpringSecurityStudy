package com.wecloud.security.distributed.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class AuthHeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Run object.
     *
     * @return the object
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities();
        HttpServletRequest request = context.getRequest();

        return null;
    }
}
