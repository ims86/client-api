package com.ims.client_api.infrastructure.repositories;

import com.ims.client_api.infrastructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);

}
