package com.epam.projectors.entities;

import java.util.HashMap;
import java.util.Map;

public class MeteoData {
  private String id;
  private Map<String, String> fields = new HashMap<String, String>();
  
  public Map<String, String> getFields() {
    return fields;
  }
  public void setFields(Map<String, String> fields) {
    this.fields = fields;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  
}
