package io.haileab.beerservice.event;

import io.haileab.beerservice.web.model.BeerDTO;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent{
    private  BeerDTO beerDTO;
}
