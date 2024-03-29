package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch Branch3=applicationContext.getBean("branch1", Branch.class);
        System.out.println(Branch3.getIfsCode()+" "+Branch3.getBranchName());
    }
}
