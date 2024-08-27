package com.ims.client_api.business.services.converter;

import com.ims.client_api.api.v1.dto.ClientDTO;
import com.ims.client_api.api.v1.dto.ClientUpdateDTO;
import com.ims.client_api.infrastructure.entities.ClientEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ClientConverter {

    public ClientEntity toEntity(ClientDTO dto){
        return ClientEntity.builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .birthdate(dto.birthdate())
                .email(dto.email())
                .insertDate(LocalDate.now())
                .build();
    }

    public ClientDTO toDto(ClientEntity entity){
        return ClientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .birthdate(entity.getBirthdate())
                .email(entity.getEmail())
                .build();
    }

    public ClientEntity toEntityUpdate (Long id, ClientUpdateDTO dto, ClientEntity entity){
        return ClientEntity.builder()
                .id(id)
                .name(dto.name() != null ? dto.name() : entity.getName())
                .email(dto.email() != null ? dto.email() : entity.getEmail())
                .birthdate(dto.birthdate() != null ? dto.birthdate() : entity.getBirthdate())
                .cpf(entity.getCpf())
                .insertDate(entity.getInsertDate())
                .updateDate(LocalDateTime.now())
                .build();
    }

    public List<ClientDTO> toListDTO(List<ClientEntity> entityList){
        return entityList.stream().map(this::toDto).toList();
    }
}
