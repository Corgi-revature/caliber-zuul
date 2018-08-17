package com.revature.caliber.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreZuulFilter extends ZuulFilter {
	@Autowired
//	private ProxyRequestHelper helper;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 10000;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

    @Override
    public Object run() {

		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		String method = request.getHeader("Authorization");
		System.out.println(request.getHeader("Authorization"));
		context.addZuulRequestHeader("jwtHeader", method);
		return null;
    }
}