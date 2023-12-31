package io.haileab.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.haileab.beerservice.domain.BeerStyleEnum;
import io.haileab.beerservice.web.model.BeerDTO;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
class BeerControllerTest {
//    @Autowired
    MockMvc mockMvc;

//    @Autowired
    ObjectMapper objectMapper;

//    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(
                        get("/api/v1/beer/" + UUID.randomUUID())
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

//    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Gergish")
                .beerStyle(BeerStyleEnum.IPA)
                .upc("455879965")
                .price(new BigDecimal(12))
                .minOnHand(52)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

//    @Test
    void updateBeerById() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("Gergish")
                .beerStyle(BeerStyleEnum.IPA)
                .upc("455879965")
                .price(new BigDecimal(12))
                .minOnHand(52)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}