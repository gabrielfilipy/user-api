package com.br.core.security;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.core.security.jwt.*;


@Component
public class AuthenticationJwFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserDatailServiceImpl userDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, 
									HttpServletResponse httpServletResponse, 
									FilterChain filterChain) throws ServletException, IOException{
		
		try {
			String jwtStr = getTokenHeader(httpServletRequest);
			if(jwtStr != null && jwtProvider.validateJwt(jwtStr)) {
				String userId = jwtProvider.getSubjectJwt(jwtStr);
				UUID userUuid = UUID.fromString(userId); // Alteração aqui
				UserDetails userDetails = userDetailService.loadUserById(userUuid);
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails( new WebAuthenticationDetailsSource()
						.buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
	private String getTokenHeader(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}

}
