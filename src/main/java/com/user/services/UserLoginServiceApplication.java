package com.user.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class UserLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	    return builder.build();
	}
	
	
	/**
	 * This bean method is to eliminate actuator api errors 
	 * @param webEndpointsSupplier
	 * @param servletEndpointsSupplier
	 * @param controllerEndpointsSupplier
	 * @param endpointMediaTypes
	 * @param corsProperties
	 * @param webEndpointProperties
	 * @param environment
	 * @return
	 */
	@Bean
	public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
	                WebEndpointsSupplier webEndpointsSupplier, 
	                ServletEndpointsSupplier servletEndpointsSupplier, 
	                ControllerEndpointsSupplier controllerEndpointsSupplier, 
	                EndpointMediaTypes endpointMediaTypes,
	                CorsEndpointProperties corsProperties, 
	                WebEndpointProperties webEndpointProperties, 
	                Environment environment) {
	    List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
	    Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
	    allEndpoints.addAll(webEndpoints);
	    allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
	    allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
	    String basePath = webEndpointProperties.getBasePath();
	    EndpointMapping endpointMapping = new EndpointMapping(basePath);
	    boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(
	                webEndpointProperties, environment, basePath);
	    return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, 
	                endpointMediaTypes, corsProperties.toCorsConfiguration(), 
	                new EndpointLinksResolver(allEndpoints, basePath), 
	                shouldRegisterLinksMapping, null);
	}

	private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, 
	                                           Environment environment, String basePath) {
	    return webEndpointProperties.getDiscovery().isEnabled() && 
	                ( 
	                ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
	}
	
}
