package one.digitalinnovation.projetodiopersonapi.service;

import one.digitalinnovation.projetodiopersonapi.dto.request.PersonDTO;
import one.digitalinnovation.projetodiopersonapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.projetodiopersonapi.entity.Person;
import one.digitalinnovation.projetodiopersonapi.exception.PersonNotFoundException;
import one.digitalinnovation.projetodiopersonapi.mapper.PersonMapper;
import one.digitalinnovation.projetodiopersonapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // indica ao Spring que será uma classe do tipo service
public class PersonService {
    // injetamos o repository
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    // o Spring vai injetar todos os objetos do tipo repository para nós atraves dessa annotation
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        // não podemos salvar o DTO, só uma entidade do banco de dodos
        // temos que converter o PersonDTO em Person
        // com o "map struct" conseguimos fazer essa conversão com uma unica linha
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse(savedPerson.getId(), "Pessoa criada com o id: ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        // converte de Person para PersonDTO
        return allPeople.stream()
                .map(personMapper::toDTO) // para cada um dos itens da lista allPeople, converte para DTO
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse(updatedPerson.getId(), "Pessoa atualizada com o id: ");
    }

    // para reaproveitamento de código dos metodos de incluir e atualizar pessoa
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    // tranformamos em metodo a verificação para reutilizar o códido
    // quando for necessario verificar a existencia do ID (usado para atualizar, deletar e buscar ID especifico)
    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
