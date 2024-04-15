package project.webservice.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.CustomerException;
import project.dao.demo.exception.CustomerInactive;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerRest {
    @Autowired
    private AccountRepository customerService;

    Logger logger= LoggerFactory.getLogger(CustomerRest.class);

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @Valid @RequestBody Customer customer) {
            try {
                // Set the customerId in the provided customer object
                customer.setCustomerId(customerId);

                // Call the service to update the customer
                Customer updatedCustomer = customerService.updateCustomer(customer);
                logger.info("Customer updated successfully");

                // Return the updated customer and HTTP status OK
                return ResponseEntity.ok(updatedCustomer);
            } catch (CustomerException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (CustomerInactive e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (ServerException e) {
                // Handle generic server exceptions and return HTTP status 500 (Internal Server Error)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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

