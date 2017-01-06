package com.cheerup.batch;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cheerup.batch.service.SolutionMatchService;

@SpringBootApplication
public class Application {
	
	@Autowired
	SolutionMatchService solutionMatchService;
	
	@PostConstruct
	public void post() throws IOException {
		solutionMatchService.doSomething();
	}
	
	
	// commit test1
	
	
	public static void main(String[] args) throws Exception {
	  ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
}
