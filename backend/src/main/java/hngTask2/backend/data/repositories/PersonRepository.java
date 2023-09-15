package hngTask2.backend.data.repositories;

import hngTask2.backend.data.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
