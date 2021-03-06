package com.bid.contract.messaging;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PublishSNSTopic {

    @Value("${sns.topic.arn}")
    private String snsTopicARN;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    private AmazonSNS amazonSNS;

    private static final Logger logger = LoggerFactory.getLogger ( PublishSNSTopic.class );

    @PostConstruct
    public void postConstructOfBean() {
        logger.info("SQS URL: " + snsTopicARN);

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider (
                new BasicAWSCredentials (awsAccessKey, awsSecretKey)
        );

        this.amazonSNS = AmazonSNSClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion)
                .build();
    }

    public String publicMessageToTopic(String message) {
        logger.info("Publishing SNS message: " + message);

        PublishResult result = this.amazonSNS.publish(this.snsTopicARN, message);

        //this.amazonSNS.get
        logger.info("SNS Message ID: " + result.getMessageId());
        return result.toString ();
    }
}
