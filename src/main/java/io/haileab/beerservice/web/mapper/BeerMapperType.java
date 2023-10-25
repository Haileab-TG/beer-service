package io.haileab.beerservice.web.mapper;

import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.web.model.BeerDTO;

public interface BeerMapperType {
    BeerDTO toBeerDto(Beer beer);
    Beer toBeer(BeerDTO beerDTO);
}
