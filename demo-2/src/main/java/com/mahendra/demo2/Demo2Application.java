package com.mahendra.demo2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(args.length < 1) {
			System.out.println("ERROR! need file path as argument.");
			return;
		}
		System.out.println("File to be uploaded to bucket : "+args[0]);
		
	}

}
