package org.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
        Branch Branch1=beanFactory.getBean("branch3", Branch.class);
        System.out.println(Branch1.getBranchContact()+" "+Branch1.getBranchName());
        Branch Branch2=beanFactory.getBean("branch4",Branch.class);
        System.out.println(Branch2);
    }
}
