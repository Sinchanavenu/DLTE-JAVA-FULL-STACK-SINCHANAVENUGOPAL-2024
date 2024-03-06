package basic.service.Files;

import basic.service.EmployeeDetails.Employee;
//import basic.service.EmployeeDetails.EmployeeDetails;

import java.io.*;
import java.util.ArrayList;

public class FilesImplementation extends Employee {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FilesImplementation<Employee> storeData= new FilesImplementation();
        storeData.empData=new ArrayList<>(10);
        storeData.readFromFile();
        storeData.readFromFile();
    }
        //for writing to file
        public void writeToFile() throws IOException {
            FileOutputStream fileOutputStream=new FileOutputStream("filepath");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(FileOutputStream);
            objectOutputStream.writeObject(empData);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        //reading from file
        public void readFromFile() throws IOException {
            FileInputStream fileInputStream=new FileInputStream();
            ObjectInputStream objectInputStream=new ObjectInputStream(FileInputStream);
            empData= (ArrayList<T>) objectInputStream.readObject();
            int size=empData.size();
            for(int index=0;index<size;index++) {
                if (empData.get(index) != null) {
                    System.out.println("Read from file\n" +empData.get(index).toString());
            objectInputStream.close();
            fileInputStream.close();
        }
    }

}
