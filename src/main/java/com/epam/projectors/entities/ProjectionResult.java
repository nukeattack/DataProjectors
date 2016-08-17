package com.epam.projectors.entities;

public class ProjectionResult {
  private MeteoData data;
  private String message;
  
  
  public MeteoData getData() {
    return data;
  }
  public void setData(MeteoData data) {
    this.data = data;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
}
