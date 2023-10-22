package io.haileab.beerservice.web.controller;

import io.haileab.beerservice.web.model.CustomerDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@Validated //for the in method validations i.e. @NotNull
public class CustomerController {

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@NotNull @PathVariable("customerId")UUID customerId){
        return new ResponseEntity<>(CustomerDTO.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewCustomer(@Valid @NotNull @RequestBody CustomerDTO customerDTO){

    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerById(
            @NotNull @PathVariable("customerId") UUID customerId,
            @NotNull @Valid @RequestBody CustomerDTO customerDTO){

    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@NotNull @PathVariable("customerId") UUID customerId){

    }



    /**
     * moved to controller advice since there is identical exception handle in BeerController
     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<List<String>> handleViolationExceptions(ConstraintViolationException e){
//        List<String> exceptions = e.getConstraintViolations().stream()
//                .map(
//                        violation -> violation.getPropertyPath() + " : " + violation.getMessage()
//                )
//                .toList();
//        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
//    }

}
