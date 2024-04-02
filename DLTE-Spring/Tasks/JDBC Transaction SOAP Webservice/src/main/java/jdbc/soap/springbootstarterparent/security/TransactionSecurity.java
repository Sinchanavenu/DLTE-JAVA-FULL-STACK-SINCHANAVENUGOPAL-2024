package jdbc.soap.springbootstarterparent.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TransactionSecurity {
    @Autowired
    private MyBankUsersService service;

    AuthenticationManager manager;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.httpBasic();
        httpSecurity.formLogin();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/Transactions/addTransaction").hasAnyAuthority("admin");
        httpSecurity.authorizeRequests().antMatchers("/Transactions/sender/{sender}").hasAnyAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/Transactions/receiver/{receiver}").hasAnyAuthority("customer");
        httpSecurity.authorizeRequests().antMatchers("/Transactions/amount/{amount}").hasAnyAuthority("customer");
        httpSecurity.authorizeRequests().anyRequest().authenticated();


        // 3rd layer
        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        manager=builder.build();
        httpSecurity.authenticationManager(manager);

        return httpSecurity.build();
    }
}