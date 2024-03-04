package basic.service;

import basic.service.Entity.UserDetails;
import basic.service.remotes.StorageTarget;
import basic.service.services.UserDetailsServices;

import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    private static StorageTarget storageTarget;
    private static UserDetailsServices services;
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private static Scanner scanner=new Scanner(System.in);
    private static UserDetails userDetails;
    public static void main( String[] args )
    {
        //storageTarget=new DatabaseTarget();
        services=new UserDetailsServices(storageTarget);
        System.out.println(resourceBundle.getString("app.greet"));
        if(userDetails==null) {
            return;
        }
        else{
            int option=0;
            do{
                System.out.println(resourceBundle.getString("app.menu"));
                option= scanner.nextInt();
                switch (option){
                    case 1:
                        System.out.println("Enter the details you wish update among (username,password,address,email,phone)");
                        String userInput= scanner.next();// available,pin
                        String[] properties=userInput.split(",");
                        int size= properties.length;
                        for(int index=0;index<size;index++){
                            if(properties[index].equalsIgnoreCase("Username")){
                                System.out.println("Enter the new username ");
                                userDetails.setUserName(scanner.nextLine());
                            }
                            if(properties[index].equalsIgnoreCase("password")){
                                System.out.println("Enter the old password");
                                if(userDetails.getPassword().equals(scanner.next())){
                                    System.out.println("Set new password");
                                    userDetails.setPassword(scanner.next());
                                }
                                else{
                                    System.out.println("Password can't set");
                                }
                            }
                            if(properties[index].equalsIgnoreCase("address")){
                                System.out.println("enter the current address ");
                                userDetails.setAddress(scanner.nextLine());
                            }
                            if(properties[index].equalsIgnoreCase("email")){
                                System.out.println("enter the email");
                                userDetails.setEmailID(scanner.next());
                            }
                            if(properties[index].equalsIgnoreCase("phone")){
                                System.out.println("enter the phone number");
                                userDetails.setPhoneNumber(scanner.nextLong());
                            }

                        }
                        services.callUpdate(userDetails);
                        break;
                    default:return;
                }
            }while (true);
        }
    }
}

