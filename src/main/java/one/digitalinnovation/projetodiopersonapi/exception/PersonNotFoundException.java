package one.digitalinnovation.projetodiopersonapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // codigo de resposta da exception
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(Long id) {
        super("Pessoa não encontrada com o ID: " + id);
    }
}
