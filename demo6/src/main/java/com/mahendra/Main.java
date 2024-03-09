package com.mahendra;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

public class Main {
    private static final String SOURCEPATH="D:\\aws-demos-1\\demo6\\src\\main\\resources\\source";
    private static final String DESTPATH="D:\\aws-demos-1\\demo6\\src\\main\\resources\\destination";
    private static final String BUCKET_NAME="buck347576";
    public static void main(String[] args) {
        
        S3Client client = S3Client.builder().region(Region.US_EAST_1).build();
        // upload(client, "document.txt");
        // upload(client, "solar-system.webp");

        System.out.println("Finding objects in buckets ....");
        List<String> objectKeys = Main.getAllObjects(client);
        for(String key : objectKeys){
            System.out.println("Object "+key);
            download(client, key);
        }


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

    public static void download(S3Client client, String filename){
        Path path = Path.of(DESTPATH, filename);       
        client.getObject(GetObjectRequest
                            .builder()
                            .bucket(BUCKET_NAME)
                            .key(filename)
                            .build(),
                        path);
        System.out.println("File downloaded at "+path.toString());
    }

    public static List<String> getAllObjects(S3Client client){
        ListObjectsResponse resp = client.listObjects(ListObjectsRequest.builder().bucket(BUCKET_NAME).build());
        List<String> keyList = new LinkedList<>();
        for(S3Object obj : resp.contents()){
            keyList.add(obj.key());
        }
        return keyList;
    }
}