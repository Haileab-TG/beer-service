package io.haileab.beerservice.event;

import io.haileab.beerservice.web.model.BeerDTO;

public class NewInventory extends BeerEvent{
    public NewInventory(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
