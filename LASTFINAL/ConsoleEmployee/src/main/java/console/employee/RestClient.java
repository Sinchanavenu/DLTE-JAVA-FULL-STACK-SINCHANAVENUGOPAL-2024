package console.employee;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RestClient {

//    static Logger logger= LoggerFactory.getLogger(RestClient.class);
//    //ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
//    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    private static final String url = "http://localhost:8082/employee/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Display All Employees");
            System.out.println("2. Display Employee by ID");
            System.out.println("3. Display Employee by Pin Code");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    readAllEmployees(httpClient);
                    break;
                case 2:
                    displayEmployeeById(httpClient, scanner);
                    break;
                case 3:
                    displayEmployeeByPinCode(httpClient, scanner);
                    break;
//                case 4:
//                    createEmployees(httpClient, scanner);
//                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }

        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAllEmployees(CloseableHttpClient httpClient) {
        try {
            HttpGet httpGet = new HttpGet(url + "allEmployee");
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String json = EntityUtils.toString(entity);
                    System.out.println("Employee Details:");
                    System.out.println(json);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayEmployeeById(CloseableHttpClient httpClient, Scanner scanner) {
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();

        try {
            HttpGet httpGet = new HttpGet(url + "employeeId/" + employeeId);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        displayEmployeeAsList(json);
                    }
                } else if (statusCode == 404) {
                    System.out.println("Employee not found.");
                } else {
                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayEmployeeAsList(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject basicDetails = jsonObject.getAsJsonObject("employeeBasicDetails");
        JsonObject permanentAddress = jsonObject.getAsJsonObject("employeePermanentAddress");
        JsonObject temporaryAddress = jsonObject.getAsJsonObject("employeeTemporaryAddress");

        System.out.println("Employee Details:");
        System.out.println("Name: " + basicDetails.get("employeeName").getAsString());
        System.out.println("Employee ID: " + basicDetails.get("employeeId").getAsString());
        System.out.println("Email: " + basicDetails.get("emailId").getAsString());
        System.out.println("Phone Number: " + basicDetails.get("phoneNumber").getAsString());
        System.out.println("Permanent Address: " + getAddressString(permanentAddress));
        System.out.println("Temporary Address: " + getAddressString(temporaryAddress));
    }

    private static String getAddressString(JsonObject address) {
        return address.get("address").getAsString() + ", " +
                address.get("houseNumber").getAsString() + ", " +
                address.get("city").getAsString() + ", " +
                address.get("state").getAsString() + " - " +
                address.get("pinCode").getAsString();
    }


//    private static void displayEmployeeById(CloseableHttpClient httpClient, Scanner scanner) {
//        System.out.print("Enter employee ID: ");
//        String employeeId = scanner.nextLine();
//
//        try {
//            HttpGet httpGet = new HttpGet(url + "employeeId/" + employeeId);
//            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//                int statusCode = response.getStatusLine().getStatusCode();
//                System.out.println("HTTP Status Code: " + statusCode);
//
//                if (statusCode == 200) {
//                    HttpEntity entity = response.getEntity();
//                    if (entity != null) {
//                        String json = EntityUtils.toString(entity);
//                        System.out.println("Employee Details:");
//                        System.out.println(json);
//                    }
//                } else if (statusCode == 404) {
//                    System.out.println("Employee not found.");
//                } else {
//                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static void displayEmployeeByPinCode(CloseableHttpClient httpClient, Scanner scanner) {
        System.out.print("Enter employee pin code: ");
        int pinCode = scanner.nextInt();
        scanner.nextLine();

        try {
            HttpGet httpGet = new HttpGet(url + "pincode/" + pinCode);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("HTTP Status Code: " + statusCode);

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        System.out.println("Employee Details:");
                        System.out.println(json);
                    }
                } else if (statusCode == 204) {
                    System.out.println("No employees found for the given pin code.");
                } else {
                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private static void createEmployees(CloseableHttpClient httpClient, Scanner scanner) {
//        System.out.println("Enter employee data in JSON format:");
//        String jsonData = scanner.nextLine();
//
//        try {
//            HttpPost httpPost = new HttpPost(BASE_URL + "create");
//            StringEntity entity = new StringEntity(jsonData);
//            httpPost.setEntity(entity);
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//
//            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
//                int statusCode = response.getStatusLine().getStatusCode();
//                System.out.println("HTTP Status Code: " + statusCode);
//
//                if (statusCode == 201) {
//                    HttpEntity responseEntity = response.getEntity();
//                    if (responseEntity != null) {
//                        String jsonResponse = EntityUtils.toString(responseEntity);
//                        System.out.println("Created Employees:");
//                        System.out.println(jsonResponse);
//                    }
//                } else {
//                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private static void createEmployees(CloseableHttpClient httpClient, Scanner scanner) {
//        try {
//
//            System.out.println("Enter employee name:");
//            String employeeName = scanner.nextLine();
//
//            System.out.println("Enter employee ID:");
//            String employeeId = scanner.nextLine();
//
//            System.out.println("Enter employee email:");
//            String email = scanner.nextLine();
//
//            System.out.println("Enter employee phone number:");
//            String phoneNumber = scanner.nextLine();
//
//            // Prompt user for permanent address details
//            System.out.println("Enter permanent address:");
//            String permanentAddress = scanner.nextLine();
//
//            System.out.println("Enter permanent house number:");
//            String permanentHouseNumber = scanner.nextLine();
//
//            System.out.println("Enter permanent state:");
//            String permanentState = scanner.nextLine();
//
//            System.out.println("Enter permanent city:");
//            String permanentCity = scanner.nextLine();
//
//            System.out.println("Enter permanent pin code:");
//            String permanentPinCode = scanner.nextLine();
//
//            // Prompt user for temporary address details
//            System.out.println("Enter temporary address:");
//            String temporaryAddress = scanner.nextLine();
//
//            System.out.println("Enter temporary house number:");
//            String temporaryHouseNumber = scanner.nextLine();
//
//            System.out.println("Enter temporary state:");
//            String temporaryState = scanner.nextLine();
//
//            System.out.println("Enter temporary city:");
//            String temporaryCity = scanner.nextLine();
//
//            System.out.println("Enter temporary pin code:");
//            String temporaryPinCode = scanner.nextLine();
//
//            // Construct JSON object
//            JsonObject employeeJson = new JsonObject();
//            employeeJson.addProperty("employeeName", employeeName);
//            employeeJson.addProperty("employeeId", employeeId);
//            employeeJson.addProperty("emailId", email);
//            employeeJson.addProperty("phoneNumber", phoneNumber);
//
//            JsonObject permanentAddressJson = new JsonObject();
//            permanentAddressJson.addProperty("address", permanentAddress);
//            permanentAddressJson.addProperty("houseNumber", permanentHouseNumber);
//            permanentAddressJson.addProperty("state", permanentState);
//            permanentAddressJson.addProperty("city", permanentCity);
//            permanentAddressJson.addProperty("pinCode", permanentPinCode);
//            employeeJson.add("permanentAddress", permanentAddressJson);
//
//            JsonObject temporaryAddressJson = new JsonObject();
//            temporaryAddressJson.addProperty("address", temporaryAddress);
//            temporaryAddressJson.addProperty("houseNumber", temporaryHouseNumber);
//            temporaryAddressJson.addProperty("state", temporaryState);
//            temporaryAddressJson.addProperty("city", temporaryCity);
//            temporaryAddressJson.addProperty("pinCode", temporaryPinCode);
//            employeeJson.add("temporaryAddress", temporaryAddressJson);
//
//            // Convert JSON object to string
//            String jsonData = employeeJson.toString();
//
//            // Send HTTP POST request
//            HttpPost httpPost = new HttpPost(BASE_URL + "create");
//            StringEntity entity = new StringEntity(jsonData);
//            httpPost.setEntity(entity);
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//
//            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
//                int statusCode = response.getStatusLine().getStatusCode();
//                System.out.println("HTTP Status Code: " + statusCode);
//
//                if (statusCode == 201) {
//                    HttpEntity responseEntity = response.getEntity();
//                    if (responseEntity != null) {
//                        String jsonResponse = EntityUtils.toString(responseEntity);
//                        System.out.println("Created Employee:");
//                        System.out.println(jsonResponse);
//                    }
//                } else {
//                    System.out.println("Error occurred. HTTP Status Code: " + statusCode);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
