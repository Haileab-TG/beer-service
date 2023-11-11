package io.haileab.beerservice.RESTclient.inventory;

import java.util.UUID;

public interface BeerInventoryServiceRestClient {
    Integer getBeerQuantityOnHand(UUID beerId);
}
