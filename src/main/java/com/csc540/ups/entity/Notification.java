package com.csc540.ups.entity;

import java.io.Serializable;

public class Notification implements Serializable {

  private String uuid;
  // univid for non-visitor, phone number for visitor
  private String contactInfo;
  private String citationID;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
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
