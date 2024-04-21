package jdbc.soap.springbootstarterparent.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.net.MalformedURLException;
import java.net.URL;

    @EnableWs
    @Configuration
    public class SoapConfig  extends WsConfigurerAdapter{

        @Bean
        public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext) {
            MessageDispatcherServlet servlet = new MessageDispatcherServlet();
            servlet.setTransformWsdlLocations(true);
            servlet.setApplicationContext(applicationContext);
            return new ServletRegistrationBean(servlet, "/transactionrepo/*");
        }

        // WSDL properties
        @Bean(name = "transaction")
        public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema) throws MalformedURLException {
            DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
            defaultWsdl11Definition.setPortTypeName("TransactionPort");
            defaultWsdl11Definition.setTargetNamespace("http://transaction.services");
            defaultWsdl11Definition.setLocationUri("/transactionrepo");
            defaultWsdl11Definition.setSchema(xsdSchema);
            return defaultWsdl11Definition;
        }

        @Bean
        public XsdSchema transactionSchema() throws MalformedURLException {
            URL url = new URL("file:///C:/DLTE-JAVA-FULL-STACK-SINCHANAVENUGOPAL-2024/DLTE-Spring/transaction.xsd");
            return new SimpleXsdSchema(new UrlResource(url));
        }

    }
