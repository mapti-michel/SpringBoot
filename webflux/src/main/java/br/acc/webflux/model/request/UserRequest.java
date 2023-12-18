package br.acc.webflux.model.request;

import br.acc.webflux.validator.TrimString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @TrimString
    @NotBlank (message = "Must not be null or empty")
    @Size(min = 3, max = 50, message = "Must be between 3 and 50 characters")
    String nome;

    @TrimString
    @NotBlank (message = "Must not be null or empty")
    @Size(min = 3, max = 20, message = "Must be between 3 and 50 characters")
    String senha;


}
