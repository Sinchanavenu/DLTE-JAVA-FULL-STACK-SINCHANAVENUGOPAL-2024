package basic.service.FilesImplementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MyBankDatabase<T> implements Activity<T> {
    ArrayList<T> bankData;
    @Override
    public String createNewData(T object) {
        bankData.add(object);
        return "data added";
    }

    public void writeTofile() throws IOException {
        //writing file
        FileOutputStream fileOutputStream=new FileOutputStream("Mybank");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bankData);
        fileOutputStream.close();
        objectOutputStream.close();
        System.out.println("Written to file");
    }
    public void readFromfile() throws IOException, ClassNotFoundException {
        //reading file
        FileInputStream fileInputStream=new FileInputStream("Mybank");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        bankData= (ArrayList<T>) objectInputStream.readObject();
        int size=bankData.size();
        for(int index=0;index<size;index++) {
            if (bankData.get(index) != null) {
                System.out.println("Read from file\n" +bankData.get(index).toString());
            }
        }
    }

    public static void main(String[] args) throws IOException, MyBankException, ClassNotFoundException {
        MyBankDatabase<CreditCard> storeCardData= new MyBankDatabase<>();
        storeCardData.bankData=new ArrayList<>(10);
        CreditCard creditCard1=new CreditCard(762345679L,"Sinchana",new Date(2034,06,29),987,100000L,2002);
        CreditCard creditCard2=new CreditCard(987654345L,"Sanjana",new Date(2031,10,18),987,75000L,2024);
        storeCardData.createNewData(creditCard1);
        storeCardData.createNewData(creditCard2);
        storeCardData.writeTofile();
        storeCardData.readFromfile();
    }
}
