package io.haileab.beerservice.RESTclient.inventory;

import java.util.UUID;

public interface BeerInventoryRestTemplate {
    Integer getBeerQuantityOnHand(UUID beerId);
}
