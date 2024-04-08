package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean(name="name1")
    @Bean
    public Branch myBean2() {
        Branch bean = new Branch();
        bean.setBranchName("sirsi");
        bean.setBankName("MyBank");
        return bean;
    }
    @Bean(name="name1")
    public Branch myBean() {
        Branch bean = new Branch();
        bean.setBankName("Baroda");
        bean.setBranchId(121);
        return bean;
    }
}

