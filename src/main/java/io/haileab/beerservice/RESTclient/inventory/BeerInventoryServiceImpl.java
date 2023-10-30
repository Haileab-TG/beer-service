package io.haileab.beerservice.RESTclient.inventory;

import io.haileab.beerservice.RESTclient.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "htg.brewery", ignoreInvalidFields = false)
@Component
public class BeerInventoryServiceImpl implements BeerInventoryService {
    private final String INVENTORY_SERVICE_PATH = "/api/v1/beer/{beerId}/inventory";
    private final RestTemplate restTemplate;
    private String beerInventoryServiceHost;

    public BeerInventoryServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getBeerQuantityOnHand(UUID beerId) {
        log.debug("Calling Inventory service");
        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
                beerInventoryServiceHost + INVENTORY_SERVICE_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BeerInventoryDto>>() {},
                (Object) beerId
        );
        Integer onHand =Objects.requireNonNull(responseEntity.getBody()).stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();
        return onHand;
    }


    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }
}
