package one.digitalinnovation.projetodiopersonapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.projetodiopersonapi.enums.PhoneType;

import javax.persistence.*;

@Entity // indica que é uma tabela do nosso banco de dados
@Data // gera os getters e setters
@Builder // utilizado para construir nossos objetos
@AllArgsConstructor // gera o construtor
@NoArgsConstructor // gera o construtor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // banco de dados gera nossa chave primária
    private Long id;

    @Enumerated(EnumType.STRING) // como é um Enum, colocamos essa annotation
    @Column(nullable = false) // esse valor não pode ser null, esse valor é obrigatório
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
