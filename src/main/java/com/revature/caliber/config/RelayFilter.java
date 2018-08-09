package com.revature.caliber.config;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class RelayFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() {
		
		RequestContext con = RequestContext.getCurrentContext();
		
		@SuppressWarnings("unchecked")
		Set<String> headers = (Set<String>) con.get("ignoredHeaders");
		System.out.println(con.get("ignoredHeaders"));
		
		headers.remove("authorization");
		System.out.println(con.get("ignoredHeaders"));
		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 10000;
	}

}
