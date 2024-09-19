package com.bank.vo;

public class Account {
    private static final String BANK_CODE = "DSA_Bank"; //  은행명
    private static int ACCOUNT_NUM = 1000;              //  계좌고유번호
    private String accNo;                               //  계좌번호
    private long balance;                               //  잔고정보

    //생성자
    public Account() {
        this.accNo = BANK_CODE + ACCOUNT_NUM++;
    }
    //getter, setter

    public String getAccNo() {
        return accNo;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    //입금하기
    public void deposit(long amount) {
        if(amount > 0) {
            this.balance += amount;
        }
    }

    //출금하기
    public void withdraw(long amount) {
        if(balance - amount < 0) {
            System.out.println("잔고가 부족합니다.");
        }
        if(balance - amount >= 0) {
            this.balance -= amount;
        }
    }

    //이체하기
    public void transfer(Account toAccount, long amount) {
        withdraw(amount);
        toAccount.deposit(amount);
    }

    @Override
    public String toString() {
        return "(계좌번호: " + accNo + ", 잔고: " + balance + ")";
    }
}
