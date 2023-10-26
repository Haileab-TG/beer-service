package io.haileab.beerservice.web.controller;

import io.haileab.beerservice.domain.BeerStyleEnum;
import io.haileab.beerservice.service.BeerService;
import io.haileab.beerservice.web.model.BeerDTO;
import io.haileab.beerservice.web.model.BeerPagedList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/beer")
@RestController
@Validated //validates the method level @NotNull Validation
@RequiredArgsConstructor
public class BeerController {
    private final BeerService beerService;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;


    @GetMapping("/beerByUpc/{upc}")
    public ResponseEntity<BeerDTO> getBeerByUPC(
            @NotNull @PathVariable("upc") String upc,
            @RequestParam(value = "showInventory",  required = false) boolean showInventory
    ){
        return new ResponseEntity<>(beerService.getBeerByUPC(upc, showInventory), HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(
            @NotNull @PathVariable("beerId") UUID beerId,
            @RequestParam(value="showInventory", required=false) boolean showInventory
    ){
        return new ResponseEntity<>(beerService.getById(beerId, showInventory), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@NotNull @Valid @RequestBody BeerDTO beerDTO){
        //@Valid validaties the Beer DTO bean validaitons
        beerService.save(beerDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeerById
            (
                @NotNull @PathVariable("beerId") UUID beerId,
                @NotNull @Valid @RequestBody BeerDTO beerDTO
            ){
        beerService.updateById(beerId, beerDTO);
    }

    @GetMapping
    public ResponseEntity<BeerPagedList> listBeers(
            @RequestParam(value = "pageNumber",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "beerName", required = false) String beerName,
            @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyleEnum,
            @RequestParam(value="showInventory", required=false) boolean showInventory
        ){
        pageNumber = pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = pageSize == null || pageSize < 0 ? DEFAULT_PAGE_SIZE : pageSize;

        BeerPagedList beerPagedList = beerService.listBeers(
                beerName, beerStyleEnum,
                PageRequest.of(pageNumber, pageSize),
                showInventory
        );

        return new ResponseEntity<>(beerPagedList, HttpStatus.OK);
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
