package com.henry.image_detector.filter;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletRequestLoggingFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String pathInfo = request.getPathInfo() != null ? request.getPathInfo() : "";

		LOG.info("{} {}{}", request.getMethod(), request.getServletPath(), pathInfo);
		try {
			chain.doFilter(request, response);
		} finally {
			LOG.info("{} {}{} (HTTP {})", request.getMethod(), request.getServletPath(), pathInfo, response.getStatus());
		}
	}
}