/*package project.webservice.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.AccountException;
import project.dao.demo.exception.CustomerException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.service.AccountService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private AccountRepository customerService;


    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
                                               @RequestBody Customer customer) {
    try {
//         Check if the provided customer ID matches the path variable
        if (!customer.getCustomerId().equals(customerId)) {
            return ResponseEntity.badRequest().build();
        }

        // Call the service method to update the customer
        Customer updatedCustomer = customerService.updateCustomer(customer);

        // Return the updated customer with a success status
        return ResponseEntity.ok(updatedCustomer);
    } catch (ServerException e) {
        // If an error occurs during the update process, return an error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

//@PutMapping("/{customerId}")
//public ResponseEntity<Object> updateCustomerService(@PathVariable Long customerId,@Valid @RequestBody Customer customer) {
//    try {
//        Customer updatedCustomer = customerService.updateCustomer(customer);
//        logger.info("Account Updated Successfully");
//        return ResponseEntity.ok(updatedCustomer);
//    } catch (AccountException accountException) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountException.getMessage());
//    }catch ( CustomerException customerException){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerException.getMessage());
//    }
//}

 */
