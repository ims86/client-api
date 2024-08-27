package com.ims.client_api.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ClientUpdateDTO(

        @NotBlank(message = "{client.field.name.required}")
        @Size(min = 3, max = 100, message = "{client.field.name.min.max}")
        String name,

        @NotNull(message = "{client.field.birthdate.required}")
        @PastOrPresent(message = "{client.field.birthdate.past}")
        @JsonFormat(pattern = "dd/MM/yyyy")
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate birthdate,

        @NotBlank(message = "{client.field.email.required}")
        @Email(message = "{client.field.email.invalid}")
        String email) {}
