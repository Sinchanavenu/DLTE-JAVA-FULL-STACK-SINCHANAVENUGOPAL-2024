package project.webservice.demo.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.*;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;


@RestController
@RequestMapping("/customer")
public class CustomerRest {
    @Autowired
    private CustomerRepository customerService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyBankCustomerService myBankCustomerService;

    Logger logger= LoggerFactory.getLogger(CustomerRest.class);

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Customer Inactive"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Object> updateCustomer(@Valid @RequestBody Customer customer) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        String username= authentication.getName();
        MyBankCustomer customer1=myBankCustomerService.findByUsername(username);

            try {
                // Set the customerId in the provided customer object
                customer.setCustomerId(customer1.getCustomerId());

                customer.setPassword(passwordEncoder.encode(customer.getPassword()));

                // Call the service to update the customer
                Customer updatedCustomer = customerService.updateCustomer(customer);
                logger.info("Customer updated successfully");

                //map to hold those attributes and display only required attributes
                Map<String, Object> responseBody = new LinkedHashMap<>();
                responseBody.put("customerName", updatedCustomer.getCustomerName());
                responseBody.put("customerAddress", updatedCustomer.getCustomerAddress());
                responseBody.put("customerStatus", updatedCustomer.getCustomerStatus());
                responseBody.put("customerContact", updatedCustomer.getCustomerContact());
                responseBody.put("username", updatedCustomer.getUsername());

                return ResponseEntity.status(HttpStatus.OK).body(responseBody);

            } catch (CustomerException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (CustomerInactive e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            } catch (ServerException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> passwordInfo) {
        try {
            String oldPassword = passwordInfo.get("oldPassword");
            String newPassword = passwordInfo.get("newPassword");
            String confirmPassword = passwordInfo.get("confirmPassword");

            // Fetch username from the authentication context
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            String updateResult = customerService.updatePassword(username, oldPassword, newPassword, confirmPassword);

            if (updateResult.equals("Password updated successfully.")) {
                return ResponseEntity.ok(updateResult);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateResult);
            }
        } catch (PasswordMismatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(MaxAttemptsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

