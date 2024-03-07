package com.mahendra;

import java.util.List;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class App {

    private static final String queueURL = "https://sqs.us-east-1.amazonaws.com/890756660068/order";
    public static void main(String[] args) {
        SqsClient client = SqsClient.builder()
                .region(Region.US_EAST_1)
                .build();
        
        // List all Queues 
        listQueues(client);
        sendMessage(client, "Hello Pune !");
    }

    static void listQueues(SqsClient client) {
        ListQueuesRequest req = ListQueuesRequest.builder().build();
        ListQueuesResponse resp = client.listQueues(req);

        List<String> queueUrls = resp.queueUrls();
        System.out.println("You have " + queueUrls.size() + " queues");
        queueUrls.forEach(qUrl -> System.out.println("Queue Url : " + qUrl));

    }

    static void sendMessage(SqsClient client, String message){
        SendMessageRequest req = SendMessageRequest.builder()
                                            .queueUrl(queueURL)
                                            .messageBody(message)
                                            .delaySeconds(2)
                                            .build();
        client.sendMessage(req);
        System.out.println("Message sent successfully !");
        
    }
}
