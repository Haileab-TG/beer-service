package io.haileab.beerservice.service;

import io.haileab.beerservice.web.model.BeerDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {
    BeerDTO getById(UUID beerId);

    BeerDTO save(BeerDTO beerDTO);

    BeerDTO updateById(UUID beerId, BeerDTO beerDTO);
}
