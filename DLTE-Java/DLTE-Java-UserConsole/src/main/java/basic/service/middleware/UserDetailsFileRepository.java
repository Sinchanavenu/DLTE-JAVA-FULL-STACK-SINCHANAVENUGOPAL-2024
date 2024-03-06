package basic.service.middleware;

import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.remotes.UserDetailsRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDetailsFileRepository implements UserDetailsRepository {
    private String filePath;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("userdetails");
    private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<UserDetails> userDetailsList=new ArrayList<>();
    public UserDetailsFileRepository(String url){
        filePath=url;
        try{
            FileHandler fileHandler=new FileHandler("credit-card-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        }
        catch (IOException ioException){}
    }

   // private void

    private void writeIntoFile(){
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(userDetailsList);

            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException ioException){}
    }

    private void readFromFile(){
        try{
            FileInputStream fileInputStream=new FileInputStream(filePath);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

            userDetailsList=(List<UserDetails>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException | ClassNotFoundException ioException){}
    }


    @Override
    public void save(UserDetails userDetails) {
        readFromFile();
        UserDetails object=userDetailsList.stream().filter(each->each.getUserName().equals(userDetails.getUserName())).findFirst().orElse(null);
        if(object!=null) {
            logger.log(Level.WARNING,userDetails.getUserName()+resourceBundle.getString("user.exists"));
            throw new UserDetailsException();
        }
        userDetailsList.add(userDetails);
        writeIntoFile();
        logger.log(Level.INFO,userDetails.getUserName()+resourceBundle.getString("user.saved"));
        System.out.println(userDetails.getUserName()+resourceBundle.getString("user.saved"));

    }

    @Override
    public void update(UserDetails userDetails) {
        readFromFile();
        UserDetails matched = userDetailsList.stream().filter(each->each.getUserName().equals(userDetails.getUserName())).findFirst().orElse(null);
        if(matched==null) {
            logger.log(Level.WARNING,userDetails.getUserName()+resourceBundle.getString("user.notExists"));
            throw new UserDetailsException(resourceBundle.getString("user.noMatches"));
        }
        int index=userDetailsList.indexOf(matched);
        userDetailsList.set(index,userDetails);
        writeIntoFile();
        logger.log(Level.FINE,resourceBundle.getString("user.update.ok"));
        System.out.println(resourceBundle.getString("user.update.ok"));

    }
    public void addUsers(UserDetails userDetails){
        readFromFile();
        userDetailsList.add(new UserDetails("sinchana","sin123",new Date(2002,03,20),"udupi","sinchana@gmail.com",7338296738L));
        userDetailsList.add(new UserDetails("annapoorna","anna123",new Date(2002,06,24),"karkala","anna@gmail.com",8765296738L));
        writeIntoFile();
    }

    public boolean verifyPassword(String userName, String password){
        UserDetails account= userDetailsList.stream().filter(each -> each.getUserName().equals())
    }
}
