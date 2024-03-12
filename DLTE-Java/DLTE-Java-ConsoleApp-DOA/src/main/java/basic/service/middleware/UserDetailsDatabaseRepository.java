package basic.service.middleware;

import basic.service.entity.UserDetails;
import basic.service.remotes.UserDetailsRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDetailsDatabaseRepository implements UserDetailsRepository {
    private Connection connection;
    //private List<CreditCard> creditCardList=new ArrayList<>();
    private Logger logger= LoggerFactory.getLogger(UserDetailsDatabaseRepository.class);
    private List<UserDetails> userDetailsList;

    private ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    //private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserDetailsDatabaseRepository(Connection connection){
        try{
            this.connection=connection;
            FileHandler fileHandler=new FileHandler("credit-card-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            //logger.addHandler(fileHandler);
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
    }

    public void save(UserDetails userDetails) {
        try{
            String query="insert into mybank_creditcard values(?,?,?,?,?,?,?,?,?)";
            preparedStatement= connection.prepareStatement(query);

            preparedStatement.setString(1,userDetails.getuserName();
            preparedStatement.setString(2,userDetails.getpassword();
            preparedStatement.setDate(3,new Date(userDetails.getdateOfBirth().getTime());
            preparedStatement.setString(4,userDetails.getaddress();
            preparedStatement.setString(5,userDetails.getemailId();
            preparedStatement.setLong(6,userDetails.getphoneNumber();

            int result = preparedStatement.executeUpdate();
            if(result!=0){
                logger.log(Level.INFO,resourceBundle.getString("record.push.ok"));
                System.out.println(resourceBundle.getString("record.push.ok"));
            }
            else{
                logger.log(Level.INFO,resourceBundle.getString("record.push.fail"));
                System.out.println(resourceBundle.getString("record.push.fail"));
            }
        }
        catch (SQLException sqlException){
            System.out.println(resourceBundle.getString("card.not.ok"));
        }
    }

    @Override
    public void addUsers() {

    }

    @Override
    public void update(UserDetails userDetails) {
        try{
            String query="update mybank_creditcard set creditcard_limit=?, creditcard_usage=?,creditcard_available=?,creditcard_pin=? where creditcard_number=?";
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1,creditCard.getCardLimit());
            preparedStatement.setInt(2,creditCard.getCardUsage());
            preparedStatement.setInt(3,creditCard.getCardAvailable());
            preparedStatement.setInt(4,creditCard.getCardPin());
            preparedStatement.setLong(5,creditCard.getCardNumber());

            int result = preparedStatement.executeUpdate();
            if(result!=0){
                logger.log(Level.INFO,resourceBundle.getString("card.update.ok"));
                System.out.println(resourceBundle.getString("card.update.ok"));
            }
            else{
                throw new CreditCardException(resourceBundle.getString("card.update.not.ok"));
            }
        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        }

    }

    @Override
    public Object verifyPassword(String username, String password) {
        return null;
    }
}
