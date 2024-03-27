package soap;

import javax.xml.ws.Endpoint;

public class EndPoint {
//    private static String url = "http://localhost:8080/employee";
//    public static void main(String[] args) {
//        EmployeeWebServiceImplementation employeeWebServiceImplementation=new EmployeeWebServiceImplementation();
//        System.out.println("Employee Web Service is published at: " + url);
//        Endpoint.publish(url,employeeWebServiceImplementation);
        public static void main(String[] args) {
            String url = "http://localhost:1005/employee";
            Endpoint.publish(url, new EmployeeWebServiceImplementation());
            System.out.println("Employee Web Service is published at: " + url);
        }
    }
