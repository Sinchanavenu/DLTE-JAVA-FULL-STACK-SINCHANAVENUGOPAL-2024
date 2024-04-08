package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //Branch myBean = context.getBean(Branch.class);//---without name
        Branch myBean = (Branch) context.getBean("myBean2");
        System.out.println(myBean.getBranchName());
        System.out.println(myBean.getBankName());
        System.out.println(myBean.getBranchName());
        System.out.println(myBean.getBranchId());
    }
}


