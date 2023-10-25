package io.haileab.beerservice.repository;

import io.haileab.beerservice.bootstrap.BeerStyleEnum;
import io.haileab.beerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepo extends JpaRepository<Beer, UUID> {
    Page<Beer> findBeerByBeerName(String beerName, Pageable pageable);
    Page<Beer> findBeerByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findBeerByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);
    Beer findBeerByUpc(String upc);
}
