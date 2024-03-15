package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger= LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
            ResourceBundle resourceBundle=ResourceBundle.getBundle("information");
            //System.setProperty("logbackConfiguration","C:\DLTE-JAVA-FULL-STACK-SINCHANAVENUGOPAL-2024\DLTE-Java\slf4j\main\\resources\\logback.xml");
            Logger logger= LoggerFactory.getLogger(App.class);
            System.out.println("hello");
            logger.info(resourceBundle.getString("check.first"));
            // logger.info("This is a test");
            System.out.println("Lets check the error");
            logger.error(resourceBundle.getString("check.second"));
            System.out.println("Warning");
            //colours are varying

            logger.warn(resourceBundle.getString("check.third"));
            logger.debug("debug testing");
            System.out.println("debug");
        }
    }
