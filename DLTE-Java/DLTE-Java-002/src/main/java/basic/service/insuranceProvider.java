package basic.service;

import java.util.Scanner;
//Input: Features expected as command line argument
//
//Output: suggest the insurance provider of each input

public class insuranceProvider {
    public static void main(String[] args) {
        /*for (int index = 0; index < args.length; index++) {
            System.out.println("Required features of insurance " + args[index]);
        }

         */
        //defining the insurance features
        String[] bajajInsurance = {"Cashless", "100% claim settlement", "recovery benefits"};
        String[] adityaBirla = {"Free health checkups", "100% claim settlement", "no room rent limits"};
        String[] avivaInsurance = {"premium", "pre existing diseases coverage", "cashless"};
        String[] starHealth = {"premium", "no claim bonus", "cashless"};
        int match[] = {0, 0, 0, 0};
        System.out.println("----------Welcome to Insurance Provider---------------");
        for (int index = 0; index < args.length; index++) {
            for (int index1 = 0; index1 < 3; index1++) {
                if (args[index].equals(bajajInsurance[index1])) {
                    match[0]++;
                }
                if (args[index].equals(adityaBirla[index1])) {
                    match[1]++;
                }
                if (args[index].equals(avivaInsurance[index1])) {
                    match[2]++;
                }
                if (args[index].equals(starHealth[index1])) {
                    match[3]++;
                }
            }
        }
        int maxValue = Integer.MIN_VALUE, returnIndex = -1;
        for (int index = 0; index < 4; index++) {
            if (match[index] >= maxValue) {
                maxValue = match[index];
                returnIndex = index;
            }
        }
        if (returnIndex == 0) {
            System.out.println("Bajaj insurance is suggested");
        } else if (returnIndex == 1) {
            System.out.println("Aditya Birla Insurance is suggested");
        } else if (returnIndex == 2) {
            System.out.println("Aviva Insurance is suggested");
        } else {
            System.out.println("Star health insurance is suggested");
        }
    }
}
