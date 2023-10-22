package io.haileab.beerservice.repository;

import io.haileab.beerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepo extends JpaRepository<Beer, UUID> {
}
