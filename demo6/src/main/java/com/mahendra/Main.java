package com.mahendra;

import java.nio.file.Path;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class Main {
    private static final String SOURCEPATH="D:\\aws-demos-1\\demo6\\src\\main\\resources\\source";
    private static final String DESTPATH="D:\\aws-demos-1\\demo6\\src\\main\\resources\\destination";
    private static final String BUCKET_NAME="buck347576";
    public static void main(String[] args) {
        
        S3Client client = S3Client.builder().region(Region.US_EAST_1).build();
        upload(client, "document.txt");
        upload(client, "solar-system.webp");

    }

    public static void upload(S3Client client, String filename){
        Path path = Path.of(SOURCEPATH, filename);
        
        PutObjectResponse resp 
                        = client.putObject(PutObjectRequest.builder()
                            .bucket(BUCKET_NAME)
                            .key(filename)
                            .build(),
                        path);
        System.out.println("File uploaded !"+ resp);
    }
}