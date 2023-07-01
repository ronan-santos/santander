package com.br.santander.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class SantanderApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Bean
	public ObjectMapper mapper() {
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.registerModule(new JavaTimeModule());
	    return mapper;
	}

}
