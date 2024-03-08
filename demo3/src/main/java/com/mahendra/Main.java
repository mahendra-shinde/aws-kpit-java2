package com.mahendra;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import java.util.Scanner;

public class Main {
    // KINDLY REPLACE TOPIC_ARN with your own topic-arn
    private static final String TOPIC_ARN="arn:aws:sns:us-east-1:890756660068:news";
    public static void main(String[] args) {
        int random = (int)(Math.random() * 50 + 1);

        SnsClient client = SnsClient.builder().region(Region.US_EAST_1).build();

        System.out.println("Sending an OTP to your registered email address !");

        PublishRequest req = PublishRequest.builder()
                                    .targetArn(TOPIC_ARN)
                                    .subject("Your OTP")
                                    .message("Your OTP is "+random+" . Kindly do not share this OTP with anyone !")
                                    .build();
        
        client.publish(req);
        Scanner sc = new Scanner(System.in);
        System.out.println("Kindly enter your OTP: ");
        String otp = sc.nextLine();

        int otpFromMail = Integer.parseInt(otp);
        if(random == otpFromMail){
            System.out.println("Authentication is successful !");
        }else{
            System.out.println("Invalid OTP ?");
        }

    }
}