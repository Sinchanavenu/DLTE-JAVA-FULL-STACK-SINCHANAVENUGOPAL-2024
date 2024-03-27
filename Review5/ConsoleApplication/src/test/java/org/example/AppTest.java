//package org.example;
//
//import backend.pojo.InputEmployeeDetails;
//import console.frontend.ConsoleApp;
//import console.frontend.Validation;
//import console.pojo.Employee1;
//import console.pojo.EmployeeAddress1;
//import console.pojo.EmployeeBasicDetails1;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertSame;
//import static org.mockito.Mockito.when;
//
//
///**
// * Unit test for simple App.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class AppTest
//{
//    /**
//     * Rigorous Test :-)
//     */
//    @Mock
//
//  ConsoleApp console;
//
//    @Mock
//    Validation validation;
//
//    @Test
//    public void testFindById(){
//        String employeeId="123";
//        Employee1 employee1=new Employee1(new EmployeeBasicDetails1("divija","cs55","divi@gmail.com",9591230448L),new EmployeeAddress1("streetA","houseA","ujire,","Karnataka",574228),new EmployeeAddress1("streetB","houseB","karnataka","mangalore",574227));
//        when(console.displayBasedOnId(scanner).thenReturn(employee1);
//        Employee1 actual=displayBasedOnEmployeeId(employeeId);
//        assertSame(employee1,actual);
//    }
//
//}