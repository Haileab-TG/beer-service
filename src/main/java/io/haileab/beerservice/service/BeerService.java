package io.haileab.beerservice.service;

import io.haileab.beerservice.domain.BeerStyleEnum;
import io.haileab.beerservice.web.model.BeerDTO;
import io.haileab.beerservice.web.model.BeerPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface BeerService {
    BeerDTO getById(UUID beerId, boolean showInventory);

    BeerDTO save(BeerDTO beerDTO);

    BeerDTO updateById(UUID beerId, BeerDTO beerDTO);

    BeerPagedList listBeers(
            String beerName,
            BeerStyleEnum beerStyle,
            PageRequest pageRequest,
            boolean showInventory
    );

    BeerDTO getBeerByUPC(String upc, boolean showInventory);
}
