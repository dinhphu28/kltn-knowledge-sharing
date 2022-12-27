package com.ndp.knowsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ndp.knowsharing.Configs.Properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
@EnableScheduling
public class KnowsharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowsharingApplication.class, args);
	}

}
