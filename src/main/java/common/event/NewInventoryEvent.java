package common.event;

import io.haileab.beerservice.web.model.BeerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewInventoryEvent {
    private BeerDTO beerDTO;
}
