package com.ims.client_api.infrastructure.config;

import com.ims.client_api.infrastructure.entities.ClientEntity;
import com.ims.client_api.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestDataConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ClientEntity c1 = new ClientEntity(1L,"Cliente 1", "48477851000", LocalDate.parse("01/01/1990", formatter), "cliente1@teste.com", LocalDate.now(), null);
        ClientEntity c2 = new ClientEntity(2L,"Cliente 2", "61394799080", LocalDate.parse("01/01/1992", formatter), "cliente2@teste.com", LocalDate.now(), null);
        ClientEntity c3 = new ClientEntity(3L,"Cliente 3", "41646905008", LocalDate.parse("01/01/1993", formatter), "cliente3@teste.com", LocalDate.now(), null);
        ClientEntity c4 = new ClientEntity(4L,"Cliente 4", "22925828051", LocalDate.parse("01/01/1994", formatter), "cliente4@teste.com", LocalDate.now(), null);
        ClientEntity c5 = new ClientEntity(5L,"Cliente 5", "39885840028", LocalDate.parse("01/01/1995", formatter), "cliente5@teste.com", LocalDate.now(), null);
        ClientEntity c6 = new ClientEntity(6L,"Cliente 6", "35831145077", LocalDate.parse("01/01/1996", formatter), "cliente6@teste.com", LocalDate.now(), null);

        clientRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

    }
}
