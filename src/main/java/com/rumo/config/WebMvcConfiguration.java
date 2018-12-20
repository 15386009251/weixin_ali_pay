package com.rumo.config;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 
 * @author Administrator
 * 
 *         WebMvcConfiguration 相当于 springmvc 的 spring-servlet.xml文件
 *
 */
@SpringBootConfiguration
public class WebMvcConfiguration implements WebMvcConfigurer {

//	
//
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginInterceptor())
//		.addPathPatterns("/admin/**","/user/**")
//		.excludePathPatterns("/admin/alipay/returnUrl","/admin/alipay/payloading",
//				"/admin/alipay/notifyUrl",
//				"/admin/weixin/notifyUrl");
//		registry.addInterceptor(localeChangeInterceptor());
	}
//
//	/**
//	 * 注册参数类型转换
//	 */
//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		resolvers.add(new UserArgumentResolver());
//	}
//
//	@Bean
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver slr = new SessionLocaleResolver();
//		// 默认语言
//		slr.setDefaultLocale(Locale.CHINA);
//		return slr;
//	}
//
//	@Bean
//	public LocaleChangeInterceptor localeChangeInterceptor() {
//		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//		// 参数名
//		lci.setParamName("lang");
//		return lci;
//	}
//	
//	/*@Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(3600)
//                .allowCredentials(true);
//    }*/
//	
//	
//	/* public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         registry.addResourceHandler("swagger-ui.html")
//         .addResourceLocations("classpath:/META-INF/resources/");
//         registry.addResourceHandler("/webjars*")
//         .addResourceLocations("classpath:/META-INF/resources/webjars/");
//     }*/

	 
}
