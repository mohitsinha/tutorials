package com.tutorials.hibernatetips.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class HibernateJsonConfig {

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void objectMapper(){
		objectMapper.registerModule(new Hibernate5Module());
	}
}
