package hngTask2.backend.data.dto.requests;
import hngTask2.backend.data.models.Address;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import static hngTask2.backend.utils.Messages.*;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class PersonRequest {


    @NotBlank(message = NOT_BLANK)
    @NotNull(message = NOT_NULL)
    private String firstName;

    @NotBlank(message = NOT_BLANK)
    @NotNull(message = NOT_NULL)
    private String lastName;

    @Pattern(regexp = "((^234)[0–9]{10})", message = INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotBlank(message = NOT_BLANK)
    @NotNull(message = NOT_NULL)
    private Address address;
}
