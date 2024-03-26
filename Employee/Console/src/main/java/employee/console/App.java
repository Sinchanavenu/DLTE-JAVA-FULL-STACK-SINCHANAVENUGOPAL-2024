package employee.console;

import employee.console.Implementation.InputDetails;
import employee.db.implementation.EmployeeExceptions;
import employee.db.implementation.ReadAndDisplayUsingDatabase;
import employee.db.interfaces.InputEmployeeDetails;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        InputEmployeeDetails employeeDetails=null;
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        InputDetails inputDetails =new InputDetails();
        try {
            employeeDetails = new ReadAndDisplayUsingDatabase();
            while (true) {
                System.out.println(resourceBundle.getString("menu.display"));
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        inputDetails.callCollectDetails(employeeDetails);
                        break;
                    case 2:
                        inputDetails.callDisplayRequired(employeeDetails);
                        break;
                    case 3:
                        inputDetails.callDisplayAll(employeeDetails);
                        break;
                    case 4:
                        inputDetails.callDisplayRequiredPincode(employeeDetails);
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        continue;
                }
            }
        }catch(EmployeeExceptions employeeExceptions){
            System.out.println(employeeExceptions.getErrorMessage());
        } catch (InputMismatchException e){
            System.out.println(resourceBundle.getString("number.only"));
            scanner.next();
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        finally {
            scanner.close();
            scanner1.close();
            scanner2.close();
            scanner3.close();
        }
    }
}
