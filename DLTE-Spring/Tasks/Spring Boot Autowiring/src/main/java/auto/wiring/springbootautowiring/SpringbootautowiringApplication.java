package auto.wiring.springbootautowiring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringbootautowiringApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringbootautowiringApplication.class);
        MyBank myBank = context.getBean(MyBank.class);
        myBank.execute();
        context.close();
    }


}
