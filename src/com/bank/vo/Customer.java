package com.bank.vo;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String ssn;
    private List<Account> accountList;

    //생성자
    public Customer(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
        accountList = new ArrayList<>();
    }

    //getter, setter


    public String getName() {
        return name;
    }

    public String getSsn() {
        return ssn;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "DSA_Bank-[고객명: " + name + ", 주민등록번호: " + ssn + "], " +
                "계좌=" + accountList;
    }
}
