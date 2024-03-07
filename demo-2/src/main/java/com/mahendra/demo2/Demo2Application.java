package com.mahendra.demo2;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner{

	private String bucketName = "test536526";
	
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filepath = null;
		if(args.length < 1) {
			System.out.println("ERROR! need file path as argument.");
			filepath = "../Readme.md";
		}else {
			filepath = args[0];
		}
		
		System.out.println("File to be uploaded to bucket : "+filepath);
		S3Client client = S3Client.builder().region(Region.US_EAST_1).build();
		System.out.println(bucketName);
		File file = new File(filepath);
		PutObjectRequest req = PutObjectRequest.builder()
									.bucket(bucketName)
									.key(file.getName())
									.build();
		
		
		if(file.isFile() ) {
			client.putObject(req,RequestBody.fromFile(file) );
			System.out.println("File "+ filepath+" uploaded successfully");
		}else {
			System.out.println("File "+ filepath +" does not exists!");
		}
	}

}
