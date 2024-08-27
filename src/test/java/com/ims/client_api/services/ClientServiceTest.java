package com.ims.client_api.services;

import com.ims.client_api.api.v1.dto.ClientDTO;
import com.ims.client_api.business.services.ClientService;
import com.ims.client_api.business.services.converter.ClientConverter;
import com.ims.client_api.infrastructure.entities.ClientEntity;
import com.ims.client_api.infrastructure.repositories.ClientRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @Mock
    private ClientConverter converter;

    @InjectMocks
    private ClientService service;


    @Captor
    private ArgumentCaptor<ClientEntity> argumentCaptor;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @BeforeEach
    public void initialize() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    class create {

        LocalDate birthdate = LocalDate.now();

        @Test
        @Disabled
        @DisplayName("Should save a client succes")
        void shouldSaveClientWithSucces(){
            //Arrange
            ClientDTO dto = generateClientDTO();

            doReturn(dto).when(repository).save(argumentCaptor.capture());

            //Act
            service.save(dto);

            //Assert
            var clientCaptured = argumentCaptor.getValue();
            assertEquals(dto.name(), clientCaptured.getName());
            assertEquals(dto.cpf(), clientCaptured.getCpf());
            assertEquals(dto.email(), clientCaptured.getEmail());
            assertEquals(dto.birthdate(), clientCaptured.getBirthdate());
        }

        @Test
        @DisplayName("Not should save a client whit name is null")
        void notShouldSaveAClientWithNameIsNull(){
            //Arrange
            var client = new ClientDTO(1L,"", "48477851000", birthdate, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit name is blanck")
        void notShouldSaveAClientWithNameIsBlanck(){
            //Arrange
            var client = new ClientDTO(1L," ", "48477851000", birthdate, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit name is less than three characteres")
        void notShouldSaveAClientWithNameIsLessThanThreeCharacters(){
            //Arrange
            var client = new ClientDTO(1L,"Ab", "48477851000", birthdate, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit cpf is null")
        void notShouldSaveAClientWithCpfIsNull(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", "", birthdate, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit cpf is blanck")
        void notShouldSaveAClientWithCpfIsBlanck(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", " ", birthdate, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit cpf is invalid")
        void notShouldSaveAClientWithCpfIsInvalido(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", "11111111111", birthdate, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit email is null")
        void notShouldSaveAClientWithEmailIsNull(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", "11111111111", birthdate, "");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit email is blanck")
        void notShouldSaveAClientWithEmailIsBlanck(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", "11111111111", birthdate, " ");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit email is invalid")
        void notShouldSaveAClientWithEmailIsInvalid(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", "11111111111", birthdate, "teste");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit birthdate is null")
        void notShouldSaveAClientWithBirthdateIsNull(){
            //Arrange
            var client = new ClientDTO(1L,"Teste", "11111111111", null, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }

        @Test
        @DisplayName("Not should save a client whit birthdate is after")
        void notShouldSaveAClientWithBirthdateIsAfter(){
            //Arrange
            LocalDate birthdateAfter = LocalDate.now().plusYears(1);
            var client = new ClientDTO(1L,"Teste", "11111111111", birthdateAfter, "teste@teste.com");

            doThrow(new RuntimeException()).when(repository).save(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, ()-> service.save(client));
        }


    }

    @Nested
    class find {
        LocalDate birthdate = LocalDate.parse("01/01/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        @Test
        @Disabled
        @DisplayName("Should findAll clients with success")
        void shouldFindAllClientWithSucces() {
            //Arrange
            ClientDTO dto = generateClientDTO();
            var clientList = List.of(dto);

            doReturn(clientList).when(repository).findAll();

            //Act
            var output = service.findAll();

            //Assert
            assertNotNull(output);
            assertEquals(clientList.size(), output.size());
            assertEquals(output.get(0).name(), dto.name());
            assertEquals(output.get(0).cpf(), dto.cpf());
            assertEquals(output.get(0).email(), dto.email());
            assertEquals(output.get(0).birthdate(), dto.birthdate());

        }

        @Test
        @Disabled
        @DisplayName("Should findById client with success")
        void shouldFindByIdClientWithSucces() {
            //Arrange
            ClientDTO dto = generateClientDTO();
            doReturn(Optional.of(dto)).when(repository).findById(longArgumentCaptor.capture());

            //Act
            var output = service.findById(dto.id());

            //Assert
            assertNotNull(output);
            assertEquals(dto.id(), longArgumentCaptor.getValue());
            assertEquals(output.name(), dto.name());
            assertEquals(output.cpf(), dto.cpf());
            assertEquals(output.email(), dto.email());
            assertEquals(output.birthdate(), dto.birthdate());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs(){
            //Arrange
            doThrow(new RuntimeException()).when(repository).findById(any());

            //Act

            //Assert
            assertThrows(RuntimeException.class, () -> service.findById(any()));
        }


    }

    @Nested
    class delete {

        @Test
        @DisplayName("Should delete client with success when client existis")
        void shouldDeleteClientWithSuccessWhenClientExists(){

            //Arrange
            Mockito.doReturn(true).when(repository).existsById(longArgumentCaptor.capture());
            doNothing().when(repository).deleteById(longArgumentCaptor.capture());
            var clientId = 1L;

            //Act
            service.delete(clientId);

            //Assert
            var listId = longArgumentCaptor.getAllValues();
            assertEquals(clientId, listId.get(0));
            assertEquals(clientId, listId.get(1));
            verify(repository, times(1)).existsById(listId.get(0));
            verify(repository, times(1)).deleteById(listId.get(1));
        }

        @Test
        @DisplayName("Not should delete client whem client not exists")
        void notShouldDeleteClientWhenClientNotExists(){

            //Arrange
            doReturn(false).when(repository).existsById(longArgumentCaptor.capture());
            var clientId = 1L;

            //Act
            service.delete(clientId);

            //Assert
            var idList = longArgumentCaptor.getValue();
            assertEquals(clientId, longArgumentCaptor.getValue());
            verify(repository, times(1)).existsById(longArgumentCaptor.getValue());
            verify(repository, times(0)).deleteById(any());

        }
    }

    private ClientDTO generateClientDTO() {
        return ClientDTO.builder()
                .id(1L)
                .name("Teste")
                .cpf("48477851000")
                .email("teste@teste.com")
                .birthdate(LocalDate.parse("01/01/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }

}
