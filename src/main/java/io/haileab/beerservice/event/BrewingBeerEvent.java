package io.haileab.beerservice.event;

import io.haileab.beerservice.web.model.BeerDTO;



public class BrewingBeerEvent extends BeerEvent{
    public BrewingBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
