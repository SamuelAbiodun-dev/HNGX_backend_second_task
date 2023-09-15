package hngTask2.backend.controller;

import com.github.fge.jsonpatch.JsonPatch;
import hngTask2.backend.data.dto.requests.PersonRequest;
import hngTask2.backend.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonRequest createPersonRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createPerson(createPersonRequest));
    }

    @GetMapping("{user_id}")
    public ResponseEntity<?> getPassengerById(@PathVariable Long user_id) {
        return ResponseEntity.ok(personService.getPersonById(user_id)
        );
    }

    @PatchMapping(value = "{user_id}", consumes = {"application/json-patch+json"})
    public ResponseEntity<?> updatePerson(@PathVariable Long user_id, @RequestBody JsonPatch updatePatch){
        try{
            return ResponseEntity.ok(personService.updatePerson(user_id, updatePatch));

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("{user_id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long user_id){
        personService.deletePerson(user_id);
        return ResponseEntity.ok(
                "Person deleted successfully"
        );
    }
}
