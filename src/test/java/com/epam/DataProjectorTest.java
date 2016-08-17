package com.epam;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.epam.projectors.DataProjector;
import com.epam.projectors.entities.MeteoData;

public class DataProjectorTest {
  @Test
  public void testDataProjector(){
    DataProjector projector = new DataProjector();
    DynamoDB dynamoDb = mock(DynamoDB.class);
    
    MeteoData data = new MeteoData();
    Context context = new TestLambdaContext(new TestLambdaLogger(DataProjectorTest.class));
    projector.handleRequest(data, context);
  }
}
