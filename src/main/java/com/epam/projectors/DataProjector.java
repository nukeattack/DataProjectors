package com.epam.projectors;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.epam.projectors.entities.MeteoData;

public class DataProjector implements RequestHandler<MeteoData, String> {
  private AmazonDynamoDBClient dynamoClient = new AmazonDynamoDBClient().withRegion(Regions.US_WEST_2);;
  private DynamoDB dynamodb = new DynamoDB(dynamoClient);
  private LambdaLogger logger;

  public String handleRequest(MeteoData input, Context context) {
    logger = context.getLogger();
    Table dataTable = dynamodb.getTable("weather_data");
    try{
      dataTable.putItem(new Item().withPrimaryKey("id", input.getId()).withMap("data", input.getFields()));
    }catch(Exception e){
      logger.log("Error saving data to table " + e.getMessage());
      return "FAILED";
    }
    return "OK";
  }

  public void setDynamodb(DynamoDB dynamodb) {
    this.dynamodb = dynamodb;
  }

}
