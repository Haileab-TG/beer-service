package io.haileab.beerservice.web.mapper;

import io.haileab.beerservice.RESTclient.inventory.BeerInventoryRestTemplate;
import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.web.model.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BeerMapperDecorator implements BeerMapperWithDecorator {
    private  BeerInventoryRestTemplate beerInventoryRestTemplate;
    private  BeerMapper beerMapper;

    @Override
    public BeerDTO toBeerDto(Beer beer) {
        BeerDTO beerDTO = beerMapper.toBeerDto(beer);
        beerDTO.setQuantityOnHand(beerInventoryRestTemplate.getBeerQuantityOnHand(beer.getId()));
        return beerDTO;
    }

    @Override
    public Beer toBeer(BeerDTO beerDTO) {
        return beerMapper.toBeer(beerDTO);
    }

    @Autowired
    public void setBeerInventoryRestTemplate(BeerInventoryRestTemplate beerInventoryRestTemplate) {
        this.beerInventoryRestTemplate = beerInventoryRestTemplate;
    }

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }
}
