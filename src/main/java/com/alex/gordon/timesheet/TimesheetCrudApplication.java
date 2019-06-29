package com.alex.gordon.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = { "com.alex.gordon.timesheet" })

public class TimesheetCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetCrudApplication.class, args);
	}

}
