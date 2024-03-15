package topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

public class Publisher {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory= new ActiveMQConnectionFactory();
        Connection connection=activeMQConnectionFactory.createConnection();
        connection.start();
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination=session.createQueue("ethnic");
        MessageProducer publisher=session.createProducer(destination);

        Scanner scanner=new Scanner(System.in);
        String info="";

        do{
            System.out.println("Send the information/message");
            info=scanner.nextLine();
            TextMessage message=session.createTextMessage(info);
            publisher.send(message);
        }while(!info.equalsIgnoreCase("lets wind up"));

        scanner.close();;
        connection.close();
    }
}
