package org.example;
/*
Consider the POJO class Transaction which we follows from Console app itself

Perform following operations over CLI based:

Create a XML which contains list of transactions
Read from XML and filter only transactions done by given user
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//creating class to add all transaction details
public class AddTransaction {
    public static void main(String[] args) {
        List<Transaction> transactionList=new ArrayList<>();
        transactionList.add(new Transaction("Sinchana",new Date(),2500,"Ramesh","Friend"));
        transactionList.add(new Transaction("Ninadha",new Date(),2300,"Suresh","Family"));
        transactionList.add(new Transaction("Sahana",new Date(),2400,"Raj","Emergency"));

        TransactionWrapper wrapper=new TransactionWrapper();
        wrapper.setTransactions(transactionList);

        try{
            JAXBContext context = JAXBContext.newInstance(TransactionWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(wrapper,new FileOutputStream("AllTransactions.xml"));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
