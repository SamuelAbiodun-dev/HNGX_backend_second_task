package hngTask2.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import hngTask2.backend.data.dto.requests.PersonRequest;
import hngTask2.backend.data.dto.responses.ApiResponse;
import hngTask2.backend.data.models.Person;
import hngTask2.backend.data.repositories.PersonRepository;
import hngTask2.backend.exception.BusinessLogicException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static hngTask2.backend.utils.Messages.MAX_NUMBER_PER_PAGE;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    @Override
    public ApiResponse createPerson(PersonRequest personRequest) {

        Person person = new Person();
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setAddress(personRequest.getAddress());
        person.setPhoneNumber(personRequest.getPhoneNumber());
        Person savedPerson = personRepository.save(person);
        return getApiResponse(savedPerson);
    }

    private static ApiResponse getApiResponse(Person savedPerson) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setId(savedPerson.getId());
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Person Created Successfully");
        return apiResponse;
    }

    @Override
    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElseThrow(
                ()-> new BusinessLogicException(
                        String.format("The person with id %d not found", personId)
                )
        );
    }

    @Override
    public Page<Person> getAllPersons(int pageNumber) {
        int page = pageNumber < 1 ? 0 : pageNumber -1;
        Pageable pageable = PageRequest.of(page, MAX_NUMBER_PER_PAGE);
        return personRepository.findAll(pageable);
    }

    @Override
    public Person updatePerson(Long personId, JsonPatch updatePayload) {
        ObjectMapper mapper = new ObjectMapper();
        Person foundPerson = getPersonById(personId);
        JsonNode node = mapper.convertValue(foundPerson, JsonNode.class);
        try {
            JsonNode updatedNode = updatePayload.apply(node);
            Person updatedPerson = mapper.convertValue(updatedNode, Person.class);
            updatedPerson.setId(foundPerson.getId());
            updatedPerson = personRepository.save(updatedPerson);
            return updatedPerson;
        } catch (JsonPatchException exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }
}
