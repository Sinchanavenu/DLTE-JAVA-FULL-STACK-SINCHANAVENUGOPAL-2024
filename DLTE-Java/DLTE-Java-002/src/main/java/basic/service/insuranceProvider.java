package basic.service;

import java.util.Scanner;

public class insuranceProvider {
    public static void main(String[] args) {
        //initialization
        String feature;
        String[] bajajInsurance ={"Cashless","100% claim settlement","recovery benefits"};
        String[] adityaBirla= {"Free health checkups","100% claim settlement","no room rent limits"};
        String[] avivaInsurance= {"premium","pre existing diseases coverage","cashless"};
        String[] starHealth = {"premium","no claim bonus","cashless"};
        Scanner scanner=new Scanner(System.in);
        //required inputs:
        System.out.println("----------Welcome to Insurance Provider---------------");
        System.out.println("Enter the insurance features required by the customer");
        feature=scanner.next();
        //process
        if(containsElement(bajajInsurance,feature)){
            System.out.println("Bajaj insurance is suggested");
        }
        if(containsElement(adityaBirla,feature))
        {
            System.out.println("Aditya birla insurance is suggested");
        }
        if(containsElement(avivaInsurance,feature))
        {
            System.out.println("Aviva insurance is suggested");
        }
        if(containsElement(starHealth,feature)){
            System.out.println("Starhealth insurance is suggested");
        }
    }
    public static boolean containsElement(String[] array, String element){
        for(String str:array){
            if(str.equals(element)){
                return true;
            }
        }
        return false;
    }
}
