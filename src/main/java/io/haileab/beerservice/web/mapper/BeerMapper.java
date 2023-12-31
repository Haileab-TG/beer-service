package io.haileab.beerservice.web.mapper;

import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper extends BeerMapperType  {

}
