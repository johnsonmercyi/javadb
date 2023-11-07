package com.javadb.bean;

import java.util.Date;
import java.util.UUID;

public class AccountType {
  private UUID id;
  private String type;
  private Date created;
  private Date updated;

  public AccountType() {
    this(null, null, null, null);
  }

  public AccountType(UUID id, String type, Date created, Date updated) {
    this.id = id;
    this.type = type;
    this.created = created;
    this.updated = updated;
  }

  /**
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the created
   */
  public Date getCreated() {
    return created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * @return the updated
   */
  public Date getUpdated() {
    return updated;
  }

  /**
   * @param updated the updated to set
   */
  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  
}
