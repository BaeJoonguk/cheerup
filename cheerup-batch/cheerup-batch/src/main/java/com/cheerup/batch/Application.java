package com.cheerup.batch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.cheerup.batch.service.SolutionMatchService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class Application {
	
	@Autowired
	SolutionMatchService SolutionMatchService;
	
	@PostConstruct
	public void post() throws IOException {
		System.out.println("hi!");
		SolutionMatchService.doSomething();
		
		init();
	}
	
	public void init() throws IOException {
	  
	}

	public static void main(String[] args) throws Exception {
	  ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
}
