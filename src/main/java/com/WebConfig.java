package com;
import javax.servlet.Filter;

import com.config.SpringJPA;
import com.config.SpringMVC;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
*@see WebConfig webapp配置
*@author eric
*@version 
*/

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    public WebConfig () {}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {SpringJPA.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {SpringMVC.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	//过滤器——字符转换器，openEntityManagerInView
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
		charFilter.setEncoding("UTF-8");
		charFilter.setForceEncoding(true);
		OpenEntityManagerInViewFilter entityfileter =new OpenEntityManagerInViewFilter();
		entityfileter.setEntityManagerFactoryBeanName("entityManagerFactory");
		return new Filter[]{charFilter,entityfileter};
	}
	
    
}