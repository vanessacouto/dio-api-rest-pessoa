package one.digitalinnovation.projetodiopersonapi.service;

import one.digitalinnovation.projetodiopersonapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.projetodiopersonapi.entity.Person;
import one.digitalinnovation.projetodiopersonapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service // indica ao Spring que será uma classe do tipo service
public class PersonService {
    // injetamos o repository
    private PersonRepository personRepository;

    // o Spring vai injetar todos os objetos do tipo repository para nós atraves dessa annotation
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);

        return MessageResponseDTO
                .builder()
                .message("Pessoa criada com o id: " + savedPerson.getId())
                .build();
    }
}
