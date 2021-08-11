package one.digitalinnovation.projetodiopersonapi.repository;

import one.digitalinnovation.projetodiopersonapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
