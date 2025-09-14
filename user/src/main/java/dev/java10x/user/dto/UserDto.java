package dev.java10x.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para criação de usuário")
public record UserDto(

        @Schema(description = "Nome do usuário", example = "João Silva")
        @NotBlank String name,

        @Schema(description = "E-mail do usuário", example = "joao@email.com")
        @NotBlank @Email String email

) {
}
