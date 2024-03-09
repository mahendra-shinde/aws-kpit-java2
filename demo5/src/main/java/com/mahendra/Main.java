package com.mahendra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.sqs.*;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class Main {
    private static final String QUEUE_URL="https://sqs.us-east-1.amazonaws.com/890756660068/mahendra-assign";
    private static final String S3_BUCKET="mahendra-assign";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message : ");
        String line = sc.nextLine();

        LocalDateTime now = LocalDateTime.now();
        String key = now.format(DateTimeFormatter.ofPattern("yyyyddMMhhmmss"));
        System.out.println("The timestamp : "+key);
        key = key +".txt";
        AWSServices.s3().putObject(
                    PutObjectRequest.builder()
                        .key(key)
                        .bucket(S3_BUCKET)
                        .contentType("text/plain").build(),
                    RequestBody.fromString(line));
        ///// OPTIONAL : Adding Attributes            
        Map<String, MessageAttributeValue> attributes = new HashMap<>();
        attributes.put("objectkey", MessageAttributeValue.builder().dataType("String").stringValue(key).build());
        /////

        AWSServices.sqs().sendMessage(SendMessageRequest
                                .builder()
                                .queueUrl(QUEUE_URL)
                                .messageBody("Successfuly uploaded an Object "+key)
                                .messageAttributes(attributes)
                                .build());
        System.out.println("Message sent !");
    }
}