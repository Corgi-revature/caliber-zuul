package com.revature.caliber.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class PreZuulFilter extends ZuulFilter {
	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Autowired
//	private ProxyRequestHelper helper;

	@Override
	public String filterType() {
		return "pre";
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 10000;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

    /* (non-Javadoc)
     * @see com.netflix.zuul.IZuulFilter#run()
     */
    @Override
    public Object run()
    {
		RequestContext context = RequestContext.getCurrentContext();
		
		HttpServletRequest request = context.getRequest();

		String method = request.getHeader("Authorization");
		
		System.out.println(request.getHeader("Authorization"));
		
		context.addZuulRequestHeader("jwtHeader", method);
		
		return null;
    }
}