package io.haileab.beerservice.service.brewing;

import io.haileab.beerservice.config.JmsConfig;
import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.event.BrewingBeerEvent;
import io.haileab.beerservice.event.NewInventoryEvent;
import io.haileab.beerservice.service.BeerService;
import io.haileab.beerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class BrewingListener {
    private final BeerService beerService;
    private final JmsTemplate jmsClient;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void brewingListener(BrewingBeerEvent event){
        BeerDTO beerDTOToBrew = event.getBeerDTO();
        Beer beer = beerService.getBeerById(beerDTOToBrew.getId());
        beerDTOToBrew.setQuantityOnHand(beer.getQuantityToBrew()); //brewing
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDTOToBrew);

        jmsClient.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
        log.debug("New inventory sent event for beer " + beer.getId());
        System.out.println("New inventory sent event for beer " + beer.getId());
    }
}
