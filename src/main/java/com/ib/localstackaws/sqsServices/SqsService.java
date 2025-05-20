package com.ib.localstackaws.sqsServices;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.ib.localstackaws.SqsModel.CustomMessage;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SqsService {


    private final AmazonSQSAsync amazonSQSAsync;

    @Value("${cloud.aws.sqs.queue-name}")
    private String queueName;

    @Value("${cloud.aws.sqs.queue-url}")
    private String queueUrl;

    public SqsService(AmazonSQSAsync amazonSQSAsync) {
        this.amazonSQSAsync = amazonSQSAsync;
    }

    public List<CustomMessage> receiveMessages() {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withMaxNumberOfMessages(10)
                .withWaitTimeSeconds(10);

        ReceiveMessageResult result = amazonSQSAsync.receiveMessage(receiveMessageRequest);
        List<Message> messages = result.getMessages();

        // Convert AWS SQS messages to our custom message format
        // Delete message from queue after processing

        return messages.stream()
                .map(message -> {
                    // Delete message from queue after processing
                    deleteMessage(message.getReceiptHandle());

                    return new CustomMessage(
                            message.getMessageId(),
                            message.getBody(),
                            LocalDateTime.now().toString()
                    );
                })
                .collect(Collectors.toList());
    }

    private void deleteMessage(String receiptHandle) {
        DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest()
                .withQueueUrl(queueUrl)
                .withReceiptHandle(receiptHandle);

        amazonSQSAsync.deleteMessage(deleteMessageRequest);
    }

}
