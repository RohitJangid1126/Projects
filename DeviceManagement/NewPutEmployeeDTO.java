package ivanovvasil.u5d5w2Project.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewPutEmployeeDTO(
        @NotEmpty(message = "The name field cannot be empty")
        @Size(min = 4, max = 30, message = "The name must be between 3 and 30 characters")
        String name,
        @NotEmpty(message = "the surname field cannot be empty")
        @Size(min = 4, max = 30, message = "The surname must be between 3 and 30 characters")
        String surname,

        @NotEmpty(message = "The email field cannot be empty")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email entered is invalid")
        String email,
        String profilePicture
) {
}
