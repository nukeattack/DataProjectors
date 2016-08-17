package com.epam;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.epam.projectors.CsvDataSender;

public class CsvDataSenderTest {
  private static final TestLambdaLogger lambdaLogger = new TestLambdaLogger(CsvDataSenderTest.class);
  
  @Test
  public void testCsvProcessing(){
    CsvDataSender sender = new CsvDataSender();
    AmazonSQSClient sqsClient = mock(AmazonSQSClient.class);
    when(sqsClient.sendMessage(any(String.class), any(String.class))).then(new Answer<SendMessageResult>() {
      public SendMessageResult answer(InvocationOnMock invocation) throws Throwable {
        System.out.println("SQS message sent * " + Arrays.toString(invocation.getArguments()));
        return null;
      }
    });
    sender.setSqsAwsClient(sqsClient);
    sender.handleRequest("sample_string", new TestContext());
  }
}
