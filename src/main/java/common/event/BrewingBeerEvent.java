package common.event;

import io.haileab.beerservice.web.model.BeerDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewingBeerEvent extends BeerEvent{
    public BrewingBeerEvent(BeerDTO beerDTO) {
        super(beerDTO);
    }
}
