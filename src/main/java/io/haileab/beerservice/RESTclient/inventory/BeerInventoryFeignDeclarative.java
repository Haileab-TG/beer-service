package io.haileab.beerservice.RESTclient.inventory;

import common.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name="beer-inventory-service")
public interface BeerInventoryFeignDeclarative {
    @GetMapping(value = BeerInventoryRestTemplate.INVENTORY_SERVICE_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(
            @PathVariable(name="beerId")UUID beerId
    );
}
