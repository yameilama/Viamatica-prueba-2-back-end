package com.lamayamei.pruebadosviamatica.Viamatica.prueba2.DTO;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    @Pattern(regexp = "^09\\d{8}$")
    private String phoneNumber;
    private String identification;
    private String username;
    private String password;
    private String email;
    @NotBlank
    @Size(min = 20, max = 100)
    private String address;
    @Size(min = 20, max = 100)
    private String addressReference;
}
