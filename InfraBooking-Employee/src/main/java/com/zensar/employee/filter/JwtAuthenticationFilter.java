package com.zensar.employee.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.zensar.olx.db.TokenStorage;
import com.zensar.olx.security.jwt.util.JwtUtil;

//This is custom filter
//we need to add this filter
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	private String authorizationHeader="Authorization";
	private final String BEARER="Bearer ";

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) 
			throws IOException, ServletException
	{
		JwtUtil jwtUtil=new JwtUtil();

		System.out.println("In doFilterInternal");
		//1.Check if user has passed token, we do that by, fetching value from Authorization header
		String authorizationHeaderValue=request.getHeader(authorizationHeader);

		//if token is not passed OR if it does not start with Bearer
		//Don't do anything proceed to next filter in chain
		if(authorizationHeaderValue==null || !authorizationHeaderValue.startsWith(BEARER))
		{
			chain.doFilter(request, response);//invoke new security filter in chain
			return;
		}
		//Following statement removes "Bearer" from token so that we can get token
//		String jwtToken=authorizationHeaderValue.substring(7).trim();
		//or
		String token=authorizationHeaderValue.substring(7);//we want to remove "Bearer " from token value
		System.out.println("Token Value="+token);
		
		//check if this token exists in cache
		String tokenExists=TokenStorage.getToken(token);
		
		//if token is null means user has logged out 
		if(tokenExists==null) {
			chain.doFilter(request, response);
			return;
		}
		
		if(authorizationHeaderValue!=null && authorizationHeaderValue.startsWith(BEARER))
		{
			
			if(token!=null)
			{
				System.out.println("authorizationHeaderValue---->"+authorizationHeaderValue);
				System.out.println("TokenValue-------->"+token);

				try {
					//validate the token
					String encodedPayload=jwtUtil.validateToken(token); //base64 payload. base64 - typical form of exchanging data on web
					//so that we don't miss any character
					//token is valid
					String payload=new String(Base64.getDecoder().decode(encodedPayload));

					//From this payload we need to fetch username
					JsonParser jsonParser=JsonParserFactory.getJsonParser();
					Map<String, Object> parseMap=jsonParser.parseMap(payload);
					String username=(String)parseMap.get("username");

					//create UsernamePasswordAuthenticationToken
					UsernamePasswordAuthenticationToken authenticationToken;
					authenticationToken=new UsernamePasswordAuthenticationToken(username,null,
							AuthorityUtils.createAuthorityList("ROLE_USER"));

					//Authenticate user
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);

				}
				catch(Exception e){
					//If token is invalid
					e.printStackTrace();
				}

			}
			System.out.println("Authorization Value"+authorizationHeaderValue);

			//2.If token not present ask user to login


			//3. If token present fetch it and validate it
			
		}
		chain.doFilter(request, response);
	}


}
