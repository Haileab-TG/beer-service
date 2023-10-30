package io.haileab.beerservice.event;

import io.haileab.beerservice.web.model.BeerDTO;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent{
    private final BeerDTO beerDTO;
}
