package com.ims.client_api.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ims.client_api.api.v1.dto.ClientDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CLIENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "{client.field.name.required}")
    @Size(min = 3, max = 100, message = "{client.field.name.min.max}")
    private String name;

    @Column(nullable = false, length = 11, unique = true)
    @NotBlank(message = "{client.field.cpf.required}")
    @CPF(message = "{client.field.cpf.invalid}")
    private String cpf;

    @Column(nullable = false, length = 10)
    @NotNull(message = "{client.field.birthdate.required}")
    @PastOrPresent(message = "{client.field.birthdate.past}")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    @Column(nullable = false, length = 100, unique = true)
    @NotBlank(message = "{client.field.email.required}")
    @Email(message = "{client.field.email.invalid}")
    private String email;

    @Column(name = "insert_date", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate insertDate;

    @Column(name = "update_date", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updateDate;


    public ClientEntity(ClientDTO dto){
        this.name = dto.name();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.birthdate = dto.birthdate();
    }

}
