package io.haileab.beerservice.RESTclient.inventory;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getBeerQuantityOnHand(UUID beerId);
}
