package io.haileab.beerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.haileab.beerservice.web.model.CustomerDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CustomerControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    final String CUSTOMER_PATH_V1 = "/api/v1/customer";


    @Test
    void getCustomerById() throws Exception {
        mockMvc.perform(get(CUSTOMER_PATH_V1 + "/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewCustomer() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .name("Xi")
                .build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDTO);
        mockMvc.perform(post(CUSTOMER_PATH_V1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomerById() throws Exception {
        CustomerDTO givenCustomerDto = CustomerDTO.builder()
                .name("Haileab")
                .build();
        String givenCustomerDtoJson = objectMapper.writeValueAsString(givenCustomerDto);

        mockMvc.perform(put(CUSTOMER_PATH_V1 + "/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(givenCustomerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCustomerById() throws Exception {
        mockMvc.perform(delete(CUSTOMER_PATH_V1 + "/" + UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }
}