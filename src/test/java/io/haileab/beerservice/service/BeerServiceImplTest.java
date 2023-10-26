package io.haileab.beerservice.service;

import io.haileab.beerservice.domain.BeerStyleEnum;
import io.haileab.beerservice.domain.Beer;
import io.haileab.beerservice.exceptions.NotFoundException;
import io.haileab.beerservice.repository.BeerRepo;
import io.haileab.beerservice.web.mapper.BeerMapperImpl;
import io.haileab.beerservice.web.mapper.DateMapper;
import io.haileab.beerservice.web.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {DateMapper.class, BeerMapperImpl.class})
class BeerServiceImplTest {
    @Mock
    BeerRepo beerRepo;

//    @Autowired
//    BeerMapper beerMapper;

    @InjectMocks
    BeerServiceImpl beerService;

    BeerDTO beerDto;
    Beer beer;

    @BeforeEach
    public void setup(){
        beerDto = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Asmera bira")
                .beerStyle(BeerStyleEnum.ALE)
                .upc("123456789")
                .price(new BigDecimal("12.0"))
                .quantityOnHand(12)
                .build();
        beer = Beer.builder()
                .id(UUID.randomUUID())
                .beerName("Asmera bira")
                .beerStyle(BeerStyleEnum.ALE)
                .upc("123456789")
                .price(new BigDecimal("12.0"))
                .quantityOnHand(12)
                .build();
    }


    @Test
    void save() {
        given(beerRepo.save(beer)).willReturn(beer);

        BeerDTO beerDTO = beerService.save(beerDto);

        assertThat(beerDTO).isNotNull();

    }
    @Test
    void getById_thenReturnsBeer() {
        given(beerRepo.findById(beer.getId())).willReturn(Optional.of(beer));


        BeerDTO beerDTO = beerService.getById(beer.getId(), false);

        assertThat(beerDTO).isNotNull();

    }

    @Test
    void getById_thenThrowsNotFoundException() {
        given(beerRepo.findById(beer.getId())).willReturn(Optional.empty());


        assertThrows(NotFoundException.class,
                () -> beerService.getById(beer.getId(), false)
                );
    }

    @Test
    void updateById() {
        given(beerRepo.findById(beer.getId())).willReturn(Optional.of(beer));
        given(beerRepo.save(beer)).willReturn(beer);

        beerDto.setBeerName("UpdatedName");

        BeerDTO updatedBeer = beerService.updateById(beer.getId(), beerDto);

        assertThat(updatedBeer).isNotNull();
        assertThat(updatedBeer.getBeerName()).isEqualTo("UpdatedName");
    }
}