package com.bank.ui;

import com.bank.service.BankManager;
import com.bank.vo.Account;
import com.bank.vo.Customer;

import java.util.Scanner;

public class BankUI {

    private BankManager manager;
    private Scanner scanner;

    public BankUI() {

        manager = new BankManager();
        scanner = new Scanner(System.in);

        while(true) {
            mainMenu();
            int input = scanner.nextInt();

            switch(input) {
                case 1: customerMenu(); break;
                case 2: accountMenu(); break;
                case 3: deposit(); break;
                case 4: withdraw(); break;
                case 5: transfer(); break;
                case 6:
                    System.out.println("프로그램을 종료 합니다.");
                    System.exit(0);
                    default:
                        System.out.println("잘못 입력 했습니다.");
            }
        }
    }

    //메인메뉴
    public void mainMenu() {
        String textblock = """
                [ DSA_BANK ]
                1. 고객관리
                2. 계좌관리
                3. 입금하기
                4. 출금하기
                5. 이체하기
                6. 프로그램 종료
                선택 >\s""";
        System.out.print(textblock);
    }

    //고객메뉴
    public void customerMenu() {
        String textblock = """
                [ 고객관리 ]
                1. 고객등록
                2. 고객검색
                3. 상위 메뉴로
                선택 >\s""";
        System.out.print(textblock);
        int input = scanner.nextInt();

        switch(input) {
            case 1: makeCustomer(); break;
            case 2: findCustomer(); break;
            case 3: return;
            default:
                System.out.println("다시 입력해주세요.");
        }
    }

    //고객 등록
    public void makeCustomer() {
        System.out.println("[ 고객등록 ]");
        System.out.print("이름 : ");
        String name = scanner.next();
        System.out.print("주민번호 : ");
        String ssn = scanner.next();

        Customer customer = new Customer(name, ssn);

        if (manager.findCustomer(customer.getSsn()) == null) {
            manager.addCustomer(customer);
            System.out.println("고객정보 등록 성공");
        } else {
            System.out.println("고객정보 등록 실패 : 해당 주민번호가 존재합니다.");
        }
    }

    //고객검색
    public void findCustomer() {
        System.out.print("검색할 고객의 주민번호 : ");
        scanner.nextLine();
        String ssn = scanner.nextLine();
        Customer findCustomer = manager.findCustomer(ssn);
        if(findCustomer != null) {
            System.out.println(findCustomer);
        } else {
            System.out.println("고객 정보가 없습니다.");
        }
    }

    //계좌메뉴
    public void accountMenu(){
        while(true) {
            String textblock = """
                    [ 계좌관리 ]
                    1. 계좌생성
                    2. 계좌검색
                    3. 상위 메뉴로
                    선택 >\s""";

            System.out.print(textblock);
            int input = scanner.nextInt();
            switch(input) {
                case 1: makeAccount(); break;
                case 2: findAccount(); break;
                case 3: return;
                default: System.out.println("다시 입력해주세요");
            }
        }
    }

    // 계좌생성
    private void makeAccount() {
        System.out.print("계좌를 생성할 고객의 주민번호 : ");
        String ssn = scanner.next();
        Customer findCustomer = manager.findCustomer(ssn);
        if(findCustomer != null) {
            System.out.print("입금할 금액 : ");
            long amount = scanner.nextLong();
            Account createdAccount = manager.createAccount(findCustomer, amount);
            System.out.println(createdAccount);
        } else {
            System.out.println("고객 정보가 없습니다.");
        }
    }

    // 계좌검색
    private void findAccount() {
        System.out.print("검색할 계좌번호 " );
        String accNo = scanner.next();
        Account findAccount = manager.findAccount(accNo);
        if(findAccount != null) {
            System.out.println(findAccount);
        } else {
            System.out.println("계좌 정보가 없습니다.");
        }
    }


    //입금하기
    public void deposit() {
        System.out.print("입금할 계좌번호 : ");
        String accNo = scanner.next();
        System.out.print("입금할 금액 : ");
        long amount = scanner.nextLong();
        System.out.println("입금 전 계좌정보 : " + manager.findAccount(accNo));
        Account account = manager.deposit(accNo, amount);
        System.out.println("입금 후 계좌정보 : " + account);
    }

    //출금하기
    public void withdraw() {
        System.out.print("출금할 계좌번호 : ");
        String accNo = scanner.next();
        System.out.print("출금할 금액 : ");
        long amount = scanner.nextLong();
        System.out.println("출금 전 계좌정보 : " + manager.findAccount(accNo));
        Account account = manager.withdraw(accNo, amount);
        System.out.println("출금 후 계좌정보 : " + account);
    }
    //이체하기
    public void transfer() {
        System.out.print("출금할 계좌번호 : ");
        String fromAccNo = scanner.next();
        System.out.print("이체할 계좌번호 : ");
        String toAccNo = scanner.next();
        System.out.print("이체할 금액 : ");
        long amount = scanner.nextLong();
        Account fromAccount = manager.transfer(fromAccNo, toAccNo, amount);
        System.out.println("이체 후 출금계좌 정보 : " + fromAccount);
    }
}
