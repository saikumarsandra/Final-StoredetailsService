package com.cts.training.storedetails.apigateway.filter;

import java.io.IOException;


import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;



public class CorsFilter implements Filter {
	



	@Override
    public void destroy() {
    }



    @Override
    public void init(FilterConfig config) throws ServletException {
   }



	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
final HttpServletResponse response = (HttpServletResponse) res;
     
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type,x-requested-with");
        response.setHeader("Access-Control-Max-Age", "3600");
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
         
        } else {
        	// forwarding the request to original destination
            chain.doFilter(req, res);
        }

	}
	
}