package com.javadb.util;

import java.util.List;
import java.util.Optional;

import com.javadb.bean.Account;
import com.javadb.bean.Transaction;
import com.javadb.service.AccountService;
import com.javadb.service.TransactionService;

public class Activities {

  private AccountService accountService;
  private TransactionService transService;

  public Activities() {
    accountService = new AccountService();
    transService = new TransactionService();
  }
  
  public boolean deposit(Transaction transaction) {
    Optional<Transaction> transOptional = transService.create(transaction);
    if (transOptional.isPresent()) {
      // set new account balance
      double newBal = transaction.getAccount().getBalance() + transaction.getAmount();
      transaction.getAccount().setBalance(newBal);

      // update the account balance
      Optional<Account> acctOptional =  accountService.update(transaction.getAccount().getId(), transaction.getAccount());

      if (acctOptional.isPresent()) {
        return true;
      } else {
        // ⚠️ We should delete the created transaction if the update is not successful.
      }
    }
    return false;
  }

  //Withdrawal
  public boolean withdrawal(Transaction transaction) {
    //check if the account bal is greater or =to the amount to be withdrawn
    if(transaction.getAccount().getBalance() >= transaction.getAmount()){
        //set new account balance
        double newBal = transaction.getAccount().getBalance() - transaction.getAmount();
        transaction.getAccount().setBalance(newBal);

        //update account 
        Optional<Account> acctOptional = accountService.update(transaction.getAccount().getId(), transaction.getAccount());
        if (acctOptional != null && acctOptional.isPresent()) {
          Optional<Transaction> transOptional = transService.create(transaction);
          if (transOptional != null && transOptional.isPresent()){
            return true;
          }
        } 
    }
    return false;
  }
  
  public boolean changePin(Account account, int oldPin, int newPin) {
    // Validate pin
    int newPinLen = Integer.toString(newPin).length();
    if (newPinLen < 4 || newPinLen > 4) {
      System.out.println("New pin must be 4 digits.");
    } else {
      if (account.getPin() == oldPin) {
        account.setPin(newPin);// update account pin
        Optional<Account> acctOptional = accountService.update(account.getId(), account);
        if (acctOptional != null && acctOptional.isPresent()) {
          return true;
        }
      } else {
        System.out.println("Incorrect pin!");
      }
    }

    return false;
  }

  public boolean bills(Transaction transaction, int pin) {
    //pin validation
    int pinLen = Integer.toString(pin).length();
    if (pinLen < 4 || pinLen > 4) {
      System.out.println("New pin must be 4 digits.");
    }else{
      if(transaction.getAccount().getPin() == pin){
        //check if the account bal is greater or =to the amount to be withdrawn
        if(transaction.getAccount().getBalance() >= transaction.getAmount()){
            //set new account balance
            double newBal = transaction.getAccount().getBalance() - transaction.getAmount();
            transaction.getAccount().setBalance(newBal);
    
            //update account 
            Optional<Account> acctOptional = accountService.update(transaction.getAccount().getId(), transaction.getAccount());
            if (acctOptional != null && acctOptional.isPresent()) {
              Optional<Transaction> transOptional = transService.create(transaction);
              if (transOptional != null && transOptional.isPresent()){
                return true;
              }
            } 
        }
      }else{
        System.out.println("Your pin does not match");
      }
    }
    return false;
  }

  public List<Transaction> transactionHistory() {
    //transaction history
    return null;
  }

}

/**
 * Deposit
 * Withdrawal
 * Check Balance
 * Change PIN
 * Recharge Card
 */
