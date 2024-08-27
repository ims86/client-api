package com.ims.client_api.api.v1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ims.client_api.api.v1.dto.ClientDTO;
import com.ims.client_api.api.v1.dto.ClientUpdateDTO;
import com.ims.client_api.business.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @InjectMocks
    ClientController controller;

    @Mock
    ClientService service;
    @Autowired
    private MockMvc mockMvc;
    private String json;
    private String url;
    private ClientDTO clientDTO;
    private ClientUpdateDTO clientUpdateDTO;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach

    void setup() throws JsonProcessingException {
        LocalDate birthdate  = LocalDate.parse("01/01/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        mockMvc = MockMvcBuilders.standaloneSetup(controller).alwaysDo(print()).build();
        url = "/api/v1/clients";
        clientDTO = clientDTO.builder()
                .name("Teste")
                .cpf("48477851000")
                .email("teste@teste.com")
                .birthdate(birthdate)
                .build();
        json = objectMapper.writeValueAsString(clientDTO);
    }

    @Nested
    class save{

        @Test
        @DisplayName("Should findAll clients with success")
        void shouldFindAllClientWithSucces() throws Exception {

            List<ClientDTO> clients = new ArrayList<ClientDTO>();
            var request = MockMvcRequestBuilders.get("/api/v1/clients");
            when(service.findAll()).thenReturn(clients);
            mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Should save a client succes")
        void shouldSaveClientWithSucces() throws Exception {

            String json = new ObjectMapper().writeValueAsString(clientDTO);

            var request = MockMvcRequestBuilders.post("/api/v1/clients")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON);

            when(service.save(clientDTO)).thenReturn(clientDTO);

            mockMvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }
}
