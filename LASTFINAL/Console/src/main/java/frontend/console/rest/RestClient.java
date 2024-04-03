package frontend.console.rest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestClient {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8112/EmployeeRestWebservice/employe");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    System.out.println("Received JSON response:");
                    System.out.println(json);
                }
            }
        else{
                System.out.println( response.getStatusLine().getStatusCode());
        }
        }catch(IOException e){
                e.printStackTrace();
            }
        finally{
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
