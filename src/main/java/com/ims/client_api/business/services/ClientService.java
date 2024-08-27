package com.ims.client_api.business.services;

import com.ims.client_api.business.services.converter.ClientConverter;
import com.ims.client_api.infrastructure.entities.ClientEntity;
import com.ims.client_api.api.v1.dto.ClientDTO;
import com.ims.client_api.api.v1.dto.ClientUpdateDTO;
import com.ims.client_api.infrastructure.exceptions.BusinessException;
import com.ims.client_api.infrastructure.exceptions.ConflictException;
import com.ims.client_api.infrastructure.exceptions.UnprocessableEntityException;
import com.ims.client_api.infrastructure.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;
    
    @Autowired
    private ClientConverter converter;


    public List<ClientDTO> findAll(){
        try{
            return converter.toListDTO(repository.findAll());
        }catch (Exception e){
            throw new BusinessException("Erro ao buscar todos os clientes" + e);
        }
        
    }

    public ClientDTO findById(Long id){
            return converter.toDto(repository.findById(id).orElseThrow(() -> new BusinessException("Cliente não localizado")));
    }

    public ClientDTO save(ClientDTO dto){

        try {
            if(existsByCpf(dto.cpf()).equals(true) || existsByEmail(dto.email()).equals(true)) {
                throw new ConflictException("Cliente já cadastrado na base de dados");
            }
            ClientEntity clientConvert = converter.toEntity(dto);

            ClientEntity client = repository.save(clientConvert);
            return converter.toDto(client);

        }catch (ConflictException e){
            throw new ConflictException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException("Erro ao cadastrar cliente" + e);
        }
    }

    public ClientDTO update(Long id, ClientUpdateDTO dto){
        try {
            ClientEntity client = repository.findById(id).orElseThrow(() -> new UnprocessableEntityException("ID não localizado na base de dados"));
            repository.save(converter.toEntityUpdate(id, dto, client));
            return converter.toDto(repository.findById(id).orElseThrow(() -> new UnprocessableEntityException("ID não localizado na base de dados")));

        }catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e){
            throw new BusinessException("Erro ao atualizar cliente" + e);
        }
    }

    public void delete(Long id){
        try {
            if(repository.existsById(id)) repository.deleteById(id);
        }catch (Exception e){
            throw new BusinessException("Erro ao deletar cliente" + e);
        }
    }

    public Boolean existsByCpf (String cpf){
        try {
            return repository.existsByCpf(cpf);
        }catch (Exception e){
            throw new BusinessException(format("Erro ao buscar cliente %s", cpf), e);
        }
    }

    public Boolean existsByEmail (String email){
        try {
            return repository.existsByEmail(email);
        }catch (Exception e){
            throw new BusinessException(format("Erro ao buscar cliente %s", email), e);
        }
    }
}
