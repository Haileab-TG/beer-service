package io.haileab.beerservice.bootstrap;

import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.repository.BeerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {
    private final BeerRepo beerRepo;

    @Override
    public void run(String... args) throws Exception {
        loadBeer();
    }

    private void loadBeer() {
        if (beerRepo.count() == 0) {
            List<Beer> bootstrapBeers = List.of(
                    Beer.builder()
                            .beerName("Asmara Bira")
                            .beerStyle(BeerStyleEnum.ALE)
                            .upc(789456123L)
                            .price(new BigDecimal(6.23))
                            .minOnHand(56)
                            .quantityToBrew(200)
                            .build(),
                    Beer.builder()
                            .beerName("Asmara Bira")
                            .beerStyle(BeerStyleEnum.ALE)
                            .upc(889456123L)
                            .price(new BigDecimal(6.23))
                            .minOnHand(56)
                            .quantityToBrew(200)
                            .build(),
                    Beer.builder()
                            .beerName("Asmara Bira")
                            .beerStyle(BeerStyleEnum.ALE)
                            .upc(989456123L)
                            .price(new BigDecimal(6.23))
                            .minOnHand(56)
                            .quantityToBrew(200)
                            .build()
            );
            beerRepo.saveAllAndFlush(bootstrapBeers);
            System.out.println("Bootstrap Loaded beers " + beerRepo.count());
        }
    }
}
