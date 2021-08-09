package one.digitalinnovation.projetodiopersonapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // controlador será acessado via API REST
@RequestMapping("/api/v1/people") // caminho de acesso principal da API (para atendender o nível 1
// do modelo de maturidade de Richardson)
public class PersonController {

    @GetMapping // operação HTTP do tipo GET
    public String getBook() {
        return "API TESTE!";
    }
}
