package com.company;

import java.text.NumberFormat;
import java.util.Scanner;

public class Mortgage {
    final private byte MONTHS_IN_YEAR = 12;
    final private byte PERCENT = 100;

    private int principal;
    private float annualInterest;
    private byte years;

    private double mortgage;

    public Mortgage(){
        setMortgageInputs();
        calculateMortgage();
    }

    private void setMortgageInputs(){
        principal = (int)readNumber("Principal", 1_000, 1_000_000);
        annualInterest = (float)readNumber("Annual Interest Rate", 0, 30);
        years = (byte)readNumber("Period (Years)", 0, 30);
    }

    public void printMortgage() {

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    private double calculateBalance(short numberOfPaymentsMade) {
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        float numberOfPayments = years * MONTHS_IN_YEAR;

        double balance = principal
                *(Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                /(Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }

    private void calculateMortgage( ){
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        float numberOfPayments = years * MONTHS_IN_YEAR;

        mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }

    private double readNumber(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt + ": ");
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
}
