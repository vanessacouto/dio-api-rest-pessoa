package one.digitalinnovation.projetodiopersonapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.projetodiopersonapi.dto.request.PersonDTO;
import one.digitalinnovation.projetodiopersonapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.projetodiopersonapi.exception.PersonNotFoundException;
import one.digitalinnovation.projetodiopersonapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2) em uma
 * interface simples e coesa (API REST).
 *
 * @author vanessa
 */
@RestController // controlador será acessado via API REST
@RequestMapping("/api/v1/people") // caminho de acesso principal da API (para atendender o nível 1
// do modelo de maturidade de Richardson)
@AllArgsConstructor(onConstructor = @__(@Autowired)) // recurso do Lombok para que possamos remover o construtor anotado com @Autowired
public class PersonController {

    private PersonService personService;

    // o Spring vai injetar todos os objetos do tipo service para nós atraves dessa annotation
    /*@Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }*/

    // @RequestBody: informa que virá uma requisição do tipo Person
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // por padrão o Spring retorna código 200, mas orientamos para ser 201
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) { // o @Valid vai fazer com que
        // as annotations de validação que colocamos na classe DTO sejam acionadas
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }
}