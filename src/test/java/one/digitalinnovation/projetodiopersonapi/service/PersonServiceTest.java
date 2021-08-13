package one.digitalinnovation.projetodiopersonapi.service;

import one.digitalinnovation.projetodiopersonapi.dto.request.PersonDTO;
import one.digitalinnovation.projetodiopersonapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.projetodiopersonapi.entity.Person;
import one.digitalinnovation.projetodiopersonapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.projetodiopersonapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.projetodiopersonapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // injeta o Mockito para rodar os testes com ele
public class PersonServiceTest {

    @Mock // para o Mockito saber que esse sera o objeto fake que criaremos
    private PersonRepository personRepository;

    @InjectMocks // para o Mockito saber que deve inserir o "personRepository" na "personService"
    private PersonService personService;

    // metodo de teste: dado um PersonDTO, retorne a mensagem de sucesso
    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        Mockito.when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        // os objetos devem ser iguais para dar sucesso
        assertEquals(expectedSuccessMessage, succesMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Pessoa criada com o id: " + id)
                .build();
    }
}
