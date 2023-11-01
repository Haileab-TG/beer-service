package io.haileab.beerservice.service.brewing;

import io.haileab.beerservice.RESTclient.inventory.BeerInventoryService;
import io.haileab.beerservice.config.JmsConfig;
import io.haileab.beerservice.domain.Beer;
import common.event.BrewingBeerEvent;
import io.haileab.beerservice.repository.BeerRepo;
import io.haileab.beerservice.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepo beerRepo;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsClient;
    private final BeerMapper beerMapper;


    @Scheduled(fixedRate = 5000)
    public void lookingToBrew(){
        List<Beer> beers = beerRepo.findAll();
        beers.forEach((beer)-> {
            Integer quantityOnHand = beerInventoryService.getBeerQuantityOnHand(beer.getId());
            if(quantityOnHand < beer.getMinOnHand()){
                jmsClient.convertAndSend(
                        JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewingBeerEvent(beerMapper.toBeerDto(beer))
                    );
                log.debug("Brewing-request message sent for " + beer.getId());
                System.out.println("Brewing-request message sent for " + beer.getId());
            }
        });
    }
}
