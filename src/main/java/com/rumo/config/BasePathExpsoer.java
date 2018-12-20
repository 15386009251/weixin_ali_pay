package com.rumo.config;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class BasePathExpsoer implements ServletContextAware {

	private ServletContext application;
	private String rootPath;

	public void init() {
		rootPath = application.getContextPath();
		application.setAttribute("rootPath", rootPath);
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.application = arg0;
	}

}