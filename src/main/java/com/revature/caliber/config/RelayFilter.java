package com.revature.caliber.config;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.revature.caliber.zuul.security.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
/**
 * This filter intercepts incoming requests in order to decode the JWT inside of the header into the parameters that the services use.
 * Checks if the custom headers already exist and denies the connection if they do, since they are nonstandard headers and should
 * not exist unless the user is attempting to access roles they do not have.
 * @authors Jack Hou,
 *		Michael Underwood
 */
@Component
public class RelayFilter extends ZuulFilter {
	
	@Autowired
	JwtConfig jwtConfig;
	
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
		
		HttpServletRequest req = con.getRequest();
		
		//If the message contains the custom headers we use treat it as an invalid message
		//as it could be an attempt to gain access that the user does not have.
		if (req.getHeader("trainer") != null || req.getHeader("role") != null) {
			con.setResponseStatusCode(401);
			con.setResponseBody("Invalid headers.");
			con.setSendZuulResponse(false);
			return null;
		}
		
		//JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		
		//converter.setVerifierKey("JwtSecretKey");
		
		String token = req.getHeader("Authorization");
		
		if (token != null) {
			Jws<Claims> claims = Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes()).parseClaimsJws(token.replace(jwtConfig.getPrefix(), ""));
			String user = claims.getBody().getSubject();
			System.out.println(claims);
			System.out.println(user);
			ArrayList<String> roles =(ArrayList) claims.getBody().get("authorities");
			System.out.println(roles.get(0));
			con.addZuulRequestHeader("trainer", user);
			con.addZuulRequestHeader("role", roles.get(0));
		}
		
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
