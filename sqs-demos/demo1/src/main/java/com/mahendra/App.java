package com.mahendra;

import java.util.List;
import java.util.Scanner;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class App {

    private static final String queueURL = "https://sqs.us-east-1.amazonaws.com/890756660068/order";
    public static void main(String[] args) {
        SqsClient client = SqsClient.builder()
                .region(Region.US_EAST_1)
                .build();
        Scanner sc = new Scanner(System.in);
        // List all Queues 
        listQueues(client);
        sendMessage(client, "Hello Hinjewadi !");
        System.out.println("Start reading messages ...");
        String choice="yes";

        while(choice.equalsIgnoreCase("yes")){
        receiveMessage(client);
        System.out.println("Do you want to poll for more messages ?");
        choice = sc.nextLine();
        }
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

    static void receiveMessage(SqsClient client){
        ReceiveMessageRequest req = ReceiveMessageRequest.builder()
                                        .queueUrl(queueURL) 
                                        .maxNumberOfMessages(5)
                                        .waitTimeSeconds(20)
                                        .visibilityTimeout(2)
                                        .build();
    
        ReceiveMessageResponse resp = client.receiveMessage(req);
        List<Message> messages =  resp.messages();
        System.out.println("Found "+messages.size()+" messages");
        for(Message msg : messages){
            System.out.println("Reading the message....");
            System.out.println("  "+msg.body());
            System.out.println("Mark as delivered (Delete from the Queue)");
            DeleteMessageRequest del = DeleteMessageRequest.builder()
                                        .queueUrl(queueURL)
                                        .receiptHandle(msg.receiptHandle())
                                        .build();
            client.deleteMessage(del);
            try{
                Thread.sleep(10);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
