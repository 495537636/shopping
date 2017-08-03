package com.sunshine.shopping.config;

import com.sunshine.shopping.interceptor.ShoppingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class DispatcherConfig extends WebMvcConfigurerAdapter {

	// /**
	// * jsp视图解析器的bean
	// */
	// @Bean
	// public InternalResourceViewResolver viewResolver() {
	// InternalResourceViewResolver viewResolver = new
	// InternalResourceViewResolver();
	// viewResolver.setPrefix("/WEB-INF/");
	// viewResolver.setSuffix(".jsp");
	// viewResolver.setViewClass(JstlView.class);
	// return viewResolver;
	// }

	/**
	 * 配置thymeleaf模版引擎html视图解析器
	 */
	@Bean(name = "templateResolver")
	public ThymeleafViewResolver thymeleafViewResolver() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/pages/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		// 此处必须设置编码，否则中文将会乱码
		templateResolver.setCharacterEncoding("UTF-8");
		templateEngine.setTemplateResolver(templateResolver);
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		// 此处也必须设置编码，否则中文将会乱码
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

	/**
	 * 配置跳转页面
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 跳转到主页
		//registry.addViewController("/").setViewName("/index");
		// 跳转到主页
		registry.addViewController("/index.html").setViewName("/index");
		// 跳转到错误页
		registry.addViewController("/error").setViewName("/common/error");
		// 跳转到登录页
		registry.addViewController("/toLogin").setViewName("/user/login");
		// 跳转到登录页
		registry.addViewController("/login.html").setViewName("/user/login");
		// 跳转到注册页
		registry.addViewController("/toRegister").setViewName("user/register");
		// 跳转到注册页
		registry.addViewController("/register.html").setViewName("user/register");
		// 跳转到找回密码页面
		registry.addViewController("/findPassword.html").setViewName("user/firstStep");
	}

	/**
	 * 配置静态资源访问
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor interceptor = new ShoppingInterceptor();
		registry.addInterceptor(interceptor).addPathPatterns("/order/*");
		super.addInterceptors(registry);
	}

}
