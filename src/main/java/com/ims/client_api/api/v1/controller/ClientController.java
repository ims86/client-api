package com.ims.client_api.api.v1.controller;

import com.ims.client_api.api.v1.dto.ClientDTO;
import com.ims.client_api.api.v1.dto.ClientUpdateDTO;
import com.ims.client_api.business.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@Tag(name = "Client-API")
public class ClientController {

    @Autowired
    private ClientService service;

    @Operation(summary = "Buscar todos os clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar clientes")
    })
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Buscar cliente por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não localizado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar cliente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Salvar novo cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar cliente")
    })
    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody @Valid ClientDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "Atualizar cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar cliente")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody @Valid ClientUpdateDTO dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Deletar cliente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar cliente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
