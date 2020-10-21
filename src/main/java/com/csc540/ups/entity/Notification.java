package com.csc540.ups.entity;

import java.io.Serializable;

public class Notification implements Serializable {

  private String id;
  // univid for non-visitor, phone number for visitor
  private String contactInfo;
  private String citationID;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
  }

  public String getCitationID() {
    return citationID;
  }

  public void setCitationID(String citationID) {
    this.citationID = citationID;
  }
}
