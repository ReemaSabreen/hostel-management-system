package com.hostel.hostel_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hostel.hostel_management.config.EnvConfig;

@SpringBootApplication
public class HostelManagementApplication {

	static {
        new EnvConfig();
    }

	public static void main(String[] args) {
		
		SpringApplication.run(HostelManagementApplication.class, args);
	}

}
