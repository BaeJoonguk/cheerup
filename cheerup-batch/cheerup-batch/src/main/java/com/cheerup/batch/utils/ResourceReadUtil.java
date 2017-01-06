package com.cheerup.batch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ResourceReadUtil {
	public static String getResource(String fileLocationInClasspath) throws IOException {
		Resource resource = new ClassPathResource(fileLocationInClasspath);
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()), 1024);
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			stringBuilder.append(line).append('\n');
		}
		br.close();
		return stringBuilder.toString();
	}
}
