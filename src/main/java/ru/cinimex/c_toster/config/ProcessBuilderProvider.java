package ru.cinimex.c_toster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

import static ru.cinimex.c_toster.constant.Constants.HOME_DIR;

@Configuration
public class ProcessBuilderProvider {

	private String directory = HOME_DIR;

	@Bean
	public ProcessBuilder processBuilder() {

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.inheritIO();
		processBuilder.directory(new File(directory));

		return processBuilder;
	}

	public String getDirectory() {
		return directory;
	}
}
