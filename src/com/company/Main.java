package com.company;

public class Main {

    public static void main(String[] args) {


        Atm  atm = new Atm("Unicredit Bank");
        Account c = new Account();
        Person  p = new Person("Ion");
        Card card = new Card(p, c, 1234);
        try {
            atm.insertCard(card);
        } catch (CardBlockedException e){
            System.out.println(e);
        }
    }
}


