package com.company;

import static com.company.Keyboard.nextInt;

class CardBlockedException extends Exception {
    public CardBlockedException(String message){
        super(message);
    }
}
class InsufficientFoundsException extends Exception {
    public InsufficientFoundsException(String msg) {
        super(msg);
    }
}
class Person {
    private String name;
    public Person (String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public String toString(){
        return this.name;
    }
}

class Card {
    private Person user;
    private Account account;
    private int pin;
    private int attemtps;
    public Card(Person user, Account account, int pin){
        this.user = user;
        this.account = account;
        this.pin = pin;
        this.attemtps = 0;
    }
    public void setAttempts(int attemtps){
        this.attemtps = attemtps;
    }
    public Person getUser() {
        return user;
    }
    public int getAttemtps(){
        return this.attemtps;
    }
    public int getPin() {
        return this.pin;
    }
    public Account getAccount() {
        return account;
    }
}
class Account {
    private double balance;
    public double checkBalance(){
        return this.balance;
    }
    public void deposit(int amount){
        if (amount > 0 ){
            this.balance= balance+ amount;
        } else {
            throw new IllegalArgumentException("Invalid argument value for parameter amount. Should be positive but found: " + amount);
        }
    }
    public void withdraw(int amount) throws InsufficientFoundsException {
        if (amount > balance){
            throw new InsufficientFoundsException("Don't have enough money in account for withdraw.");
        }else {
            this.balance = balance - amount;
        }
    }
}





class Atm {
    private String nameOfBank;
    private Card card;
    public Atm(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }
    public void insertCard(Card card) throws CardBlockedException {
        System.out.println("Card was inserted \n\n");
        this.card = card;
        int inputPin = 0;
        while (this.card.getAttemtps() < 3 && card.getPin() != inputPin){
            System.out.print("Insert pin code: ");
            inputPin = nextInt();
            if (inputPin == this.card.getPin()){
                System.out.println("Welcome: " + this.card.getUser().getName()+"."+ " \n" + "ACCES to your account. \n *MENU*") ;
                this.showMenu();
            } else {
                this.card.setAttempts( this.card.getAttemtps() + 1 ) ;
                System.out.println(" Acces denied! \n Try again.");
            }
        }
        if (this.card.getAttemtps() == 3){
            throw new CardBlockedException("Your Card is blocked!! Check your bank to unlock your Credit Card.");
        }
    }
    private void showMenu()   {
        int option = 0;
        while (option != 4) {
            System.out.println("1.Deposit amount.");
            System.out.println("2.Withdraw amount.");
            System.out.println("3.Check Balance.");
            System.out.println("4.Exit.");
            option = nextInt();
            switch (option) {
                case 1: {
                    System.out.println("-------------------");
                    System.out.println(" Enter sum for deposit.");
                    System.out.println("-------------------");
                    int depositAmount = nextInt();
                    this.card.getAccount().deposit(depositAmount);
                    System.out.println("You added in your account sum of " + depositAmount + "  $ ");
                    System.out.println("*******************");
                    break;
                }
                case 2: {
                    System.out.println("-------------------");
                    System.out.println("Enter sum for withdraw.");
                    System.out.println("-------------------");
                    int withdrawAmount = nextInt();
                    try {
                        this.card.getAccount().withdraw(withdrawAmount);
                        System.out.println(" Withdraw to your account sum of " + withdrawAmount + " $");
                    } catch (InsufficientFoundsException e) {
                        System.out.println(e);
                    }
                    System.out.println("*******************");
                    break;
                }
                case 3: {
                    System.out.println("------------------");
                    System.out.println("Your Balance is: " +  this.card.getAccount().checkBalance() + " $");
                    System.out.println("------------------");
                    break;
                }
                case 4: {
                    System.out.println("------------------");
                    System.out.println("Operation ended " + "\n" + "Have a nice day! ");
                    System.out.println("------------------");
                }
                default: {
                    break;
                }
            }
        }
    }
}

