package io.haileab.beerservice.RESTclient.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerInventoryRestTemplateTest {
    @Autowired
    BeerInventoryServiceRestClient beerInventoryServiceRestClient;

    @Test
    void getBeerQuantityOnHand() {
        Integer qoh = beerInventoryServiceRestClient.getBeerQuantityOnHand(UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08"));
        assertThat(qoh).isNotNull();
        System.out.println("Quntity on hand " + qoh );

    }
}