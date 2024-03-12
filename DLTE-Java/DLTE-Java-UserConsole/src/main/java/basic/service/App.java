package basic.service;

import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.middleware.FileStorageTarget;
import basic.service.middleware.UserDetailsFileRepository;
import basic.service.remotes.StorageTarget;
import basic.service.services.UserDetailsServices;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.exit;

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
    //private static Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());
    private static Logger logger= LoggerFactory.getLogger(UserDetailsFileRepository.class);
    public static void main( String[] args )
    {
        int option;
        String username, password;

        storageTarget = new FileStorageTarget();
        services = new UserDetailsServices(storageTarget);
        System.setProperty("logbackConfiguration","logback.xml");

        System.out.println(resourceBundle.getString("app.login.menu"));
        option = scanner.nextInt();
        services.callAddUsers();
        if (option == 1) {
            System.out.println("Enter Your Username");
            username = scanner.next();
            System.out.println("Enter Password");
            password = scanner.next();
            userDetails=services.callVerifyPassword(username, password);
            if (userDetails!=null) {

                while (true) {
                    System.out.println(resourceBundle.getString("app.dashboard.menu"));
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            updateUserDetails();
                            break;
                        case 6:
                            System.out.println("Thank You for choosing our Bank");
                            exit(0);
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            } else {
                System.out.println("Login failed. Exiting...");
            }
        }
        else{
            System.out.println("Option not available");
        }
    }

        private static void updateUserDetails() {
        System.out.println("Enter the details you wish to update among (password, address, email, phone)");
        String userInput = scanner.next();
        String[] properties = userInput.split(",");
        int size = properties.length;

        for (int index = 0; index < size; index++) {
            try {
                if (properties[index].equalsIgnoreCase("phone")) {
                    System.out.println("Enter the new phone number ");
                    userDetails.setphoneNumber(scanner.nextLong());
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if (properties[index].equalsIgnoreCase("address")) {
                System.out.println("Enter the new address");
                scanner.nextLine();
                userDetails.setaddress(scanner.nextLine());
            }
            if (properties[index].equalsIgnoreCase("email")) {
                System.out.println("Enter the email ");
                scanner.nextLine();
                userDetails.setemailId(scanner.nextLine());
            }
            if (properties[index].equalsIgnoreCase("password")) {
                System.out.println("Enter the old password");
                scanner.nextLine();
                if (userDetails.getpassword().equals(scanner.nextLine())) {
                    System.out.println("Set new password");
                    userDetails.setpassword(scanner.nextLine());
                } else {
                    System.out.println("Password can't be set");
                    logger.info(userDetails.getuserName() + resourceBundle.getString("update.failed"));
                    exit(0);
                }
            }
        }

        try {
            services.callUpdate(userDetails);
            //System.out.println("User details updated successfully.");
            logger.info(userDetails.getuserName() + resourceBundle.getString("user.update.done"));
        } catch (UserDetailsException e) {
            System.out.println("Failed to update user details: " + e.getMessage());

        }
    }
}

