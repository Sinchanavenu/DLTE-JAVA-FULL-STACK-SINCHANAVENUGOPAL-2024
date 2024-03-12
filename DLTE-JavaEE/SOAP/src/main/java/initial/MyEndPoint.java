package initial;

import javax.xml.ws.Endpoint;

public class MyEndPoint {
    private static String url="http://localhost:1000/sinchana";

    public static void main(String[] args) {
        MySource mySource=new MySource();
        System.out.println("SOAP web service running" +url);
        Endpoint.publish(url,mySource);
    }
}
