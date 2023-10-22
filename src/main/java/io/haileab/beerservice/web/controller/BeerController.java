package io.haileab.beerservice.web.controller;

import io.haileab.beerservice.web.model.BeerDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/beer")
@RestController
@Validated //validates the method level @NotNull Validation
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@NotNull @PathVariable("beerId") UUID beerId){
        //todo impl
        return new ResponseEntity<>(BeerDTO.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@NotNull @Valid @RequestBody BeerDTO beerDTO){
        //@Valid validaties the Beer DTO bean validaitons
        //todo impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeerById
            (
                @NotNull @PathVariable("beerId") UUID beerId,
                @NotNull @Valid @RequestBody BeerDTO beerDTO
            ){
        //todo impl
    }


    /**
     * Moved to advice controller since there is identical exception handler in CustomerController
     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<List<String>> validationExceptionHander(ConstraintViolationException e){
//        List<String> exceptions = new ArrayList<>(e.getConstraintViolations().size());
//        e.getConstraintViolations().forEach(
//                violation -> exceptions.add(violation.getPropertyPath() + " : " + violation.getMessage())
//        );
//        System.out.println(exceptions);
//        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
//    }

}
