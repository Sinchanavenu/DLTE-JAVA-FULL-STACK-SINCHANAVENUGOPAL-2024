package basic.service.middleware;

import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.remotes.UserDetailsRepository;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class UserDetailsFileRepository implements UserDetailsRepository {
    private String filePath;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("userdetails");
    //private Logger logger = Logger.getLogger(UserDetailsFileRepository.class.getName());
    private Logger logger= LoggerFactory.getLogger(UserDetailsFileRepository.class);
    private List<UserDetails> userDetailsList;
    private static Scanner scanner=new Scanner(System.in);

    public UserDetailsFileRepository(String url) {
        filePath = url;
        userDetailsList = new ArrayList<>();
        //System.setProperty("system.output.ansi.enabled","always");
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
                // If the file doesn't exist, create a new file
            }
//            FileHandler fileHandler = new FileHandler("User-details-logs.txt",true);
//            SimpleFormatter simpleFormatter = new SimpleFormatter();
//            fileHandler.setFormatter(simpleFormatter);
//            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void writeIntoFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(userDetailsList);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void readFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            userDetailsList = (List<UserDetails>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }


    @Override
    public void save(UserDetails userDetails) {
        readFromFile();

        if (userDetailsList.stream().anyMatch(u -> u.getuserName().equals(userDetails.getuserName()))) {
            //logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("user.exists"));
            logger.error(userDetails.getuserName()+resourceBundle.getString("user.exists"));
            throw new UserDetailsException(resourceBundle.getString("user.exists"));
        }

        userDetailsList.add(userDetails);
        writeIntoFile();
        logger.info(userDetails.getuserName()+resourceBundle.getString("user.saved"));
        //logger.log(Level.INFO, userDetails.getuserName() + resourceBundle.getString("user.saved"));
        System.out.println(userDetails.getuserName() + resourceBundle.getString("user.saved"));
    }

//    @Override
//    public void addUsers() {
//        readFromFile();
////        userDetailsList.add(new UserDetails("annapoornapai", "annap@123", new Date(2002, 7, 6), "karkala", "annapoorna@gmail.com", 9876543210L));
////        userDetailsList.add(new UserDetails("sinchanavenu", "sinchanav@123", new Date(2002, 8, 5), "mulki", "sinchanaa@gmail.com", 9876547680L));
////        userDetailsList.add(new UserDetails("shreyam", "shre@123", new Date(2002, 11, 12), "moodbidri", "shreya@gmail.com", 9876512345L));
////        userDetailsList.add(new UserDetails("akshatha", "aksh@123", new Date(2002, 11, 20), "karkala", "akshatha@gmail.com", 9765443210L));
////        userDetailsList.add(new UserDetails("arundhathi","aruparu",new Date(2003,2,8),"Biloor","aru@gmail.com", 9282983228L));
//        writeIntoFile();
//
//    }

    @Override
    public void update(UserDetails userDetails) {
        readFromFile();

        UserDetails matched = userDetailsList.stream()
                .filter(each -> each.getuserName().equals(userDetails.getuserName()))
                .findFirst()
                .orElse(null);

        if (matched == null) {
            //logger.log(Level.WARNING, userDetails.getuserName() + resourceBundle.getString("user.notExists"));
            logger.error(userDetails.getuserName()+resourceBundle.getString("user.noMatches"));
            throw new UserDetailsException(resourceBundle.getString("user.noMatches"));
        }
        int index = userDetailsList.indexOf(matched);
        userDetailsList.set(index, userDetails);
        writeIntoFile();
        System.out.println("Credential updated for "+userDetails.getuserName());
        //logger.log(Level.FINE, resourceBundle.getString("user.update.ok"));
        logger.info(userDetails.getuserName()+resourceBundle.getString("user.update.ok"));
        System.out.println(resourceBundle.getString("user.update.ok"));
    }


    @Override
    public Object verifyPassword(String username, String password) {
        readFromFile();
        UserDetails account = userDetailsList.stream()
                .filter(each -> each.getuserName().equals(username))
                .findFirst()
                .orElse(null);
        try {
            if (account == null) {
                System.out.println(resourceBundle.getString("username.not.found"));
                logger.info(getAllUserDetails()+resourceBundle.getString("username.not.found"));
                return null;
            } else if (!account.getpassword().equals(password)) {
                logger.info(getAllUserDetails()+resourceBundle.getString("password.not.matched"));
                System.out.println(resourceBundle.getString("password.not.matched"));
                throw new UserDetailsException();
            } else
                return null;
        }catch(UserDetailsException userDetailsException){
            for(int attempts=2;attempts<=3;){
                System.out.println(resourceBundle.getString("login.fail")+" Only "+(3-attempts+1)+" attempts left");
                logger.info(getAllUserDetails()+resourceBundle.getString("login.fail"));
                System.out.println(userDetailsException);
                String pass=scanner.next();
                if(account.getpassword().equals(pass)){
                    System.out.println(resourceBundle.getString("login.success"));
                    logger.info(getAllUserDetails()+resourceBundle.getString("login.success"));
                    return account;
                }else{
                    //   System.out.println(resourceBundle.getString("accounts.login.fail")+" Only "+(3-attempts)+" attempts left");;
                    attempts++;
                }if(attempts>3) {
                    System.out.println(resourceBundle.getString("accounts.no.more.attempts"));
                    logger.info(getAllUserDetails()+resourceBundle.getString("accounts.no.more.attempts"));
                }
            }
        }
        return account;
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        readFromFile();
        return new ArrayList<>(userDetailsList);
    }

    @Override
    public void addUsers() {

    }
}
