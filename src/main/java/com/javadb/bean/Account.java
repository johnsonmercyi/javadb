package com.javadb.bean;

import java.sql.Date;
import java.util.UUID;

public class Account {
  private UUID id;
  private Customer customer;
  private AccountType accountType;
  private double balance;
  private int pin;
  private int accountNo;
  private Date created;
  private Date updated;

  public Account() {
    this(UUID.randomUUID(), null, null, 0, 0, 0);
  }

  public Account(UUID id, Customer customer, AccountType accountType, double balance, int pin, int accountNo) {
    this.id = id;
    this.customer = customer;
    this.accountType = accountType;
    this.balance = balance;
    this.pin = pin;
    this.accountNo = accountNo;
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
   * @return the customer
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * @param customer the customer to set
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * @return the accountType
   */
  public AccountType getAccountType() {
    return accountType;
  }

  /**
   * @param accountType the accountType to set
   */
  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  /**
   * @return the balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * @param balance the balance to set
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * @return the pin
   */
  public int getPin() {
    return pin;
  }

  /**
   * @param pin the pin to set
   */
  public void setPin(int pin) {
    this.pin = pin;
  }

  /**
   * @return the accountNo
   */
  public int getAccountNo() {
    return accountNo;
  }

  /**
   * @param accountNo the accountNo to set
   */
  public void setAccountNo(int accountNo) {
    this.accountNo = accountNo;
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
    return "Account {\n"
        + "\tid: " + id + ",\n"
        + "\tcustomer: " + customer.getFirstname() + " " + customer.getLastname() + ",\n"
        + "\taccountType: " + accountType.getType() + ",\n"
        + "\tbalance: " + balance + ",\n"
        + "\tpin: " + pin + ",\n"
        + "\taccountNo: " + accountNo + ",\n"
        + "\tcreated: " + created + ",\n"
        + "\tupdated: " + updated + "\n"
        + "}\n";
  }

}
