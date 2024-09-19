package com.bank.service;

import com.bank.vo.Account;
import com.bank.vo.Customer;

import java.util.ArrayList;
import java.util.List;

public class BankManager {
    List<Customer> customerList = new ArrayList<>();

    //고객 등록
    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    //고객 검색
    public Customer findCustomer(String ssn) {
        for (Customer customer : customerList) {
            if (customer.getSsn().equals(ssn)) {
                return customer;
            }
        }
        return null;
    }

    //계좌 등록
    public Account createAccount(Customer customer, long amount) {
        Account account = new Account();
        account.deposit(amount);
        customer.getAccountList().add(account);
        return account;
    }

    //계좌 검색
    public Account findAccount(String accNo) {
        for (Customer customer : customerList) {
            for (Account account : customer.getAccountList()) {
                if(account.getAccNo().equals(accNo)) {
                    return account;
                }
            }
        }
        return null;
    }

    //입금하기
    public Account deposit(String accNo, long amount) {
        Account findAccount = findAccount(accNo);
        if (findAccount != null) {
            findAccount.deposit(amount);
            return findAccount;
        }
        return null;
    }

    //출금하기
    public Account withdraw(String accNo, long amount) {
        Account findAccount = findAccount(accNo);
        if (findAccount != null) {
            findAccount.withdraw(amount);
            return findAccount;
        }
        return null;
    }

    //이체하기
    public Account transfer(String fromAccNo, String toAccNo, long amount) {
        Account fromAccount = findAccount(fromAccNo);
        Account toAccount = findAccount(toAccNo);

        if (fromAccount != null && toAccount != null) {
            fromAccount.transfer(toAccount, amount);
            return fromAccount;
        }
        return null;
    }
}
