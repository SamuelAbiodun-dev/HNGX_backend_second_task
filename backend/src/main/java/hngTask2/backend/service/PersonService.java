package hngTask2.backend.service;

import com.github.fge.jsonpatch.JsonPatch;
import hngTask2.backend.data.dto.requests.PersonRequest;
import hngTask2.backend.data.dto.responses.ApiResponse;
import hngTask2.backend.data.models.Person;
import org.springframework.data.domain.Page;

public interface PersonService {
    ApiResponse createPerson(PersonRequest personRequest);
    Person getPersonById(Long personId);
    Page<Person> getAllPersons(int pageNumber);
    Person updatePerson(Long personId, JsonPatch updatePayload);
    void deletePerson(Long personId);


}
