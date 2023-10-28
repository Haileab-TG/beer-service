package io.haileab.beerservice.bootstrap;

import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.domain.BeerStyleEnum;
import io.haileab.beerservice.repository.BeerRepo;
import io.haileab.beerservice.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//Used by localmysql profile
@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
    private final BeerRepo beerRepo;
    private final BeerService beerService;

    private static final String UPC_1 = "0631234200036";
    private static final String UPC_2 = "0631234300019";
    private static final String UPC_3 = "0083783375213";

    @Override
    public void run(String... args) throws Exception {
        if(beerRepo.count() == 0){
            loadBeer();
        }
        getByIdTest();
    }


    private void loadBeer() {
        if (beerRepo.count() == 0) {
            List<Beer> bootstrapBeers = List.of(
                    Beer.builder()
                            .beerName("Mango Bobs")
                            .beerStyle(BeerStyleEnum.ALE)
                            .upc(UPC_1)
                            .price(new BigDecimal("6.23"))
                            .quantityOnHand(56)
                            .quantityToBrew(200)
                            .build(),
                    Beer.builder()
                            .beerName("Galaxy Cat")
                            .beerStyle(BeerStyleEnum.ALE)
                            .upc(UPC_2)
                            .price(new BigDecimal("12.95"))
                            .quantityOnHand(56)
                            .quantityToBrew(200)
                            .build(),
                    Beer.builder()
                            .beerName("Pinball Porter")
                            .beerStyle(BeerStyleEnum.ALE)
                            .upc(UPC_3)
                            .price(new BigDecimal("14.45"))
                            .quantityOnHand(56)
                            .quantityToBrew(200)
                            .build()
            );
            beerRepo.saveAllAndFlush(bootstrapBeers);
            System.out.println("Bootstrap Loaded beers " + beerRepo.count());
        }
    }
    private void getByIdTest() {
        Beer beer = beerRepo.findBeerByUpc(UPC_1);
        System.out.println("Saved beer id " + beer.getId());
        beerService.getById(beer.getId(), false);
    }

}
