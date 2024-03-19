package basic.service.middleware;

import basic.service.Entity.Transactions;
import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.remotes.UserDetailsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.event.Level;

public class UserDetailsDatabaseRepository implements UserDetailsRepository {
    UserDetails userDetails=new UserDetails();
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("userdetails");
    private Logger logger= LoggerFactory.getLogger(UserDetailsDatabaseRepository.class);
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    //private Logger logger = Logger.getLogger(UserDetailsDatabaseRepository.class);

    public UserDetailsDatabaseRepository(Connection connection) {
        this.connection=connection;
//        try{
//
////            FileHandler fileHandler=new FileHandler("User-details-logs.txt",true);
////            SimpleFormatter simpleFormatter=new SimpleFormatter();
////            fileHandler.setFormatter(simpleFormatter);
////            logger.addHandler(fileHandler);
//        }
//        catch (IOException ioException){
//            System.out.println(ioException);
//        }
    }


//    @Override
//    public void addUsers() {
//
//    }


    @Override
    public void update(UserDetails userDetails) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE UserDetails SET password=?, dob=?, address=?, email=?, phone=? WHERE username=?");
            statement.setString(1, userDetails.getpassword());
            statement.setDate(2, new java.sql.Date(userDetails.getdateOfBirth().getTime()));
            statement.setString(3, userDetails.getaddress());
            statement.setString(4, userDetails.getemailId());
            statement.setLong(5, userDetails.getphoneNumber());
            statement.setString(6, userDetails.getuserName());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                logger.error(userDetails.getuserName()+resourceBundle.getString("user.notExists"));
                throw new UserDetailsException("User does not exist.");
            }
//            else {
//                logger.info(userDetails.getuserName()+resourceBundle.getString("credential.updated"));
//            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Object verifyPassword(String username, String password) {
//        int attempts = 3; // Number of attempts allowed
//        Scanner scanner = new Scanner(System.in);
//
//        while (attempts > 0) {
//            try {
//                PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserDetails WHERE username=?");
//                statement.setString(1, username);
//                ResultSet resultSet = statement.executeQuery();
//                if (resultSet.next()) {
//                    String storedPassword = resultSet.getString("password");
//                    if (password.equals(storedPassword)) {
//                        UserDetails userDetails = new UserDetails(
//                                resultSet.getString("username"),
//                                resultSet.getString("password"),
//                                resultSet.getDate("dob"),
//                                resultSet.getString("address"),
//                                resultSet.getString("email"),
//                                resultSet.getLong("phone")
//                        );
//                        System.out.println(resourceBundle.getString("login.success"));
//                        return userDetails;
//                    } else {
//                        attempts--;
//                        if (attempts == 0) {
//                            logger.error(userDetails.getuserName()+resourceBundle.getString("account.locked"));
//                            throw new UserDetailsException("Too many failed attempts. Account locked.");
//                        }
//                        System.out.println("Incorrect password. Attempts left: " + attempts);
//                        System.out.print("Enter password: ");
//                        password = scanner.nextLine();
//                    }
//                } else {
//                    logger.error(userDetails.getuserName()+resourceBundle.getString("user.notfound"));
//                    throw new UserDetailsException("Username not found.");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        scanner.close();
//        return null;
        int attempts = 3; // Number of attempts allowed
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserDetails WHERE username=?");
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    if (password.equals(storedPassword)) {
                        UserDetails userDetails = new UserDetails(
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getDate("dob"),
                                resultSet.getString("address"),
                                resultSet.getString("email"),
                                resultSet.getLong("phone")
                        );
                        System.out.println(resourceBundle.getString("login.success"));
                        return userDetails;
                    } else {
                        attempts--;
                        if (attempts == 0) {
                            logger.error(username + resourceBundle.getString("account.locked"));
                            throw new UserDetailsException("Too many failed attempts. Account locked.");
                        }
                        System.out.println("Incorrect password. Attempts left: " + attempts);
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();
                    }
                } else {
                    attempts--;
                    if (attempts == 0) {
                        logger.error(username + resourceBundle.getString("account.locked"));
                        throw new UserDetailsException("Too many failed attempts. Account locked.");
                    }
                    System.out.println("Username not found. Attempts left: " + attempts);
                    System.out.print("Enter username: ");
                    username = scanner.next();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public List<Transactions> findAll() {
        ArrayList<Transactions> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions";
            preparedStatement=connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Date date=resultSet.getDate(2);
                if(date!=null)
                    transactionArrayList.add(new Transactions(date,resultSet.getLong(1),resultSet.getString(5),resultSet.getDouble(3),resultSet.getDouble(4)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

    @Override
    public List<Transactions> findAllUsers(String username) {
        ArrayList<Transactions> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions where username=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transactions(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(5),resultSet.getDouble(3),resultSet.getDouble(4)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }


    @Override
    public List<Transactions> findAllByDate(Date date, String username) {
        ArrayList<Transactions> transactionArrayList=new ArrayList<>();
        try{
            String query="select * from transactions where username=? and transaction_date=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setDate(2, (java.sql.Date) date);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                transactionArrayList.add(new Transactions(resultSet.getDate(2),resultSet.getLong(1),resultSet.getString(5),resultSet.getDouble(3),resultSet.getDouble(4)));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }
        return transactionArrayList;
    }

}


