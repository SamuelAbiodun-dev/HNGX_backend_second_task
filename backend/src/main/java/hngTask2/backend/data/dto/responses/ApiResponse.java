package hngTask2.backend.data.dto.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private Long id;
    private String message;
    private boolean isSuccess;
}
