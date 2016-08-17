package com.epam.projectors;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.epam.projectors.entities.MeteoData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CsvDataSender implements RequestHandler<String, Object> {
  private ObjectMapper mapper = new ObjectMapper();
  private AmazonSQSClient sqsAwsClient = new AmazonSQSClient();

  LambdaLogger logger;

  public Object handleRequest(String input, Context context) {
    logger = context.getLogger();

    for (int i = 0; i < 10; i++) {
      MeteoData data = new MeteoData();
      data.setId(UUID.randomUUID().toString());
      for (int j = 0; j < 10; j++) {
        data.getFields().put("field_" + j, UUID.randomUUID().toString());
      }
      try {
        sendSQSMessage(data);
      } catch (Exception e) {
        logger.log("Error processing message, " + e.getMessage());
      }
    }
    return "SUCCESS";
  }

  private void sendSQSMessage(MeteoData data) throws Exception {
    String message;
    message = mapper.writeValueAsString(data);
    sqsAwsClient.sendMessage("https://sqs.us-west-2.amazonaws.com/261625240164/weather_data", mapper.writeValueAsString(data));
    logger.log(message);
  }
  
  public void setSqsAwsClient(AmazonSQSClient sqsAwsClient) {
    this.sqsAwsClient = sqsAwsClient;
  }  
}
