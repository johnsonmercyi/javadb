package com.javadb.bean;

import java.util.Date;
import java.util.UUID;

public class Transaction {
  private UUID id;
  private Account account;
  private String type; // CREDIT, DEBIT
  private String typeDescription; // DEPOSIT, WITHDRAWAL, BILLS
  private String beneficiary;
  private double amount;
  private String description;
  private Date created;
  private Date updated;

  public enum Type {
    CREDIT, DEBIT;
  }

  public enum TypeDescription {
    DEPOSIT, WITHDRAWAL, BILLS;
  }

  public Transaction() {
    this(UUID.randomUUID(), null, null, null, null, 0, null, null, null);
  }

  public Transaction(UUID id,
      Account account, String type, String typeDescription, String beneficiary, double amount, String description,
      Date created, Date updated) {
    this.id = id;
    this.account = account;
    this.type = type;
    this.typeDescription = typeDescription;
    this.beneficiary = beneficiary;
    this.amount = amount;
    this.description = description;
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
   * @return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * @param account the account to set
   */
  public void setAccount(Account account) {
    this.account = account;
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
   * @return the typeDescription
   */
  public String getTypeDescription() {
    return typeDescription;
  }

  /**
   * @param typeDescription the typeDescription to set
   */
  public void setTypeDescription(String typeDescription) {
    this.typeDescription = typeDescription;
  }

  /**
   * @return the beneficiary
   */
  public String getBeneficiary() {
    return beneficiary;
  }

  /**
   * @param beneficiary the beneficiary to set
   */
  public void setBeneficiary(String beneficiary) {
    this.beneficiary = beneficiary;
  }

  /**
   * @return the amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * @param amount the amount to set
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
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
    return "Transaction {\n"
        + "\tid: " + id + ",\n"
        + "\tcustomer: " + account.getCustomer().getFirstname() + " " + account.getCustomer().getLastname() + ",\n"
        + "\taccountNo: " + account.getAccountNo() + ",\n"
        + "\ttype: " + type + ",\n"
        + "\ttypeDesc: " + typeDescription + ",\n"
        + "\tamount: " + amount + ",\n"
        + "\tdescription: " + description + ",\n"
        + "\tbeneficiary: " + beneficiary + ",\n"
        + "\tcreated: " + created + ",\n"
        + "\tupdated: " + updated + "\n"
        + "}";
  }

}
