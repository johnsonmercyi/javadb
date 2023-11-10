package com.javadb.bean;

import java.util.Date;
import java.util.UUID;

public class Customer {
  private UUID id;
  private String firstname;
  private String lastname;
  private String address;
  private Date created;
  private Date updated;

  public Customer() {
    this(UUID.randomUUID(), null, null, null, null, null);
  }

  public Customer(UUID id, String firstname, String lastname, String address, Date created, Date updated) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
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
   * @return the firstname
   */
  public String getFirstname() {
    return firstname;
  }

  /**
   * @param firstname the firstname to set
   */
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  /**
   * @return the lastname
   */
  public String getLastname() {
    return lastname;
  }

  /**
   * @param lastname the lastname to set
   */
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  /**
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address the address to set
   */
  public void setAddress(String address) {
    this.address = address;
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

  @Override
  public String toString() {
    return String.format("{ %s, %s, %s, %s, %s, %s }", 
    id, firstname, lastname, address, created != null ? created.toString(): created, updated != null ? updated.toString() : updated);
  }
  
}