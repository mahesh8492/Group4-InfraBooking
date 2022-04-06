package com.zensar.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zensar"})
public class InfraBookingRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraBookingRoomApplication.class, args);
	}

}
