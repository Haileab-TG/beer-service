package io.haileab.beerservice.RESTclient.inventory;

import common.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("local-discovery")
@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryOpenFeign implements BeerInventoryServiceRestClient{
    private final BeerInventoryFeignDeclarative beerInventoryFeignDeclarative;
    @Override
    public Integer getBeerQuantityOnHand(UUID beerId) {
        log.debug("BeerInventoryOpenFeign -> getBeerQuantityOnHand : Calling Inventory service with OPEN FEIGN");
        ResponseEntity<List<BeerInventoryDto>> responseEntity =
                beerInventoryFeignDeclarative.getOnHandInventory(beerId);
        Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
        return onHand;
    }
}
