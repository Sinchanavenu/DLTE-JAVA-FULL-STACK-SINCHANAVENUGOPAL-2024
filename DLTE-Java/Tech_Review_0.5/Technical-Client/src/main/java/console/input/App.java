package console.input;

import console.input.presentation.FilterEmployeeByPincode;
import console.input.presentation.InputEmployeeProfile;
import console.input.presentation.OutputEmployeeProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.exit;

public class App
{

        private static ResourceBundle resourceBundle = ResourceBundle.getBundle("clientApp");
        private static Logger logger = LoggerFactory.getLogger(App.class);
        private static Scanner scanner=new Scanner(System.in);
        private static InputEmployeeProfile inputEmployeeProfile=new InputEmployeeProfile();
        private static OutputEmployeeProfile outputEmployeeProfile=new OutputEmployeeProfile();
        private static FilterEmployeeByPincode filterEmployeeByPincode=new FilterEmployeeByPincode();

        public static void main(String[] args) {

                try {
                        while (true) {
                                logger.info(resourceBundle.getString("app.greet"));
                                System.out.println(resourceBundle.getString("app.greet"));
                                System.out.println(resourceBundle.getString("app.menu"));
                                int choice = 0;
                                        try {
                                                choice = scanner.nextInt();
                                        } catch (InputMismatchException e) {
                                                logger.warn(resourceBundle.getString("app.menu.exception"));
                                                System.out.println(resourceBundle.getString("app.menu.exception"));
                                                scanner.nextLine();
                                        }
                                switch (choice) {
                                        case 1:
                                                String option;
                                                 do {
                                                        inputEmployeeProfile.inputEmployeeDetails();
                                                        inputEmployeeProfile.inputEmployeePermanentAddress();
                                                        inputEmployeeProfile.inputEmployeeTemporaryAddress();

                                                         logger.info(resourceBundle.getString("app.request.again"));
                                                        System.out.println(resourceBundle.getString("app.request.again"));
                                                        do {
                                                                option = scanner.next();
                                                                if (!option.matches("(?i)(Yes|No)")) {
                                                                        logger.warn(resourceBundle.getString("app.request.error"));
                                                                        System.out.println(resourceBundle.getString("app.request.error"));
                                                                }
                                                        } while (!option.matches("(?i)(Yes|No)"));
                                                } while (option.equalsIgnoreCase("Yes"));
                                                break;

                                        case 2:
                                                try {

                                                        outputEmployeeProfile.outputEmployeeProfile();

                                                } catch (Exception e) {
                                                        logger.error("Error occurred while fetching and printing employee profiles: " + e.getMessage());
                                                        System.out.println("An error occurred while fetching and printing employee profiles. Please try again later.");
                                                }
                                                break;
                                        case 3:
                                                try{
                                                        filterEmployeeByPincode.filterByPincode();
                                } catch (Exception e) {
                                        logger.error("Error occurred while fetching and printing employee profiles: " + e.getMessage());
                                        System.out.println("An error occurred while fetching and printing employee profiles. Please try again later.");
                                }
                                break;

                                        case 4:
                                                logger.info(resourceBundle.getString("app.exit"));
                                                System.out.println(resourceBundle.getString("app.exit"));
                                                exit(0);
                                        default:
                                                logger.warn(resourceBundle.getString("app.default"));
                                                System.out.println(resourceBundle.getString("app.default"));
                                }
                        }
                } catch (Exception e) {
                        logger.error(resourceBundle.getString("app.exception"));
                        System.out.println(resourceBundle.getString("app.exception") + e.getMessage());
                        e.printStackTrace();
                }
        }



}
