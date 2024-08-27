package com.ims.client_api.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@Builder
public record ClientDTO(


        Long id,

        @NotBlank(message = "{client.field.name.required}")
        @Size(min = 3, max = 100, message = "{client.field.name.min.max}")
        String name,

        @NotBlank(message = "{client.field.cpf.required}")
        @CPF(message = "{client.field.cpf.invalid}")
        String cpf,

        @NotNull(message = "{client.field.birthdate.required}")
        @PastOrPresent(message = "{client.field.birthdate.past}")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate birthdate,

        @NotBlank(message = "{client.field.email.required}")
        @Email(message = "{client.field.email.invalid}")
        String email) {}
