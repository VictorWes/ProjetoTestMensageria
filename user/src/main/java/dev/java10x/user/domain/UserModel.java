package dev.java10x.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TB_USERS")
@Schema(description = "Entidade de usuário do sistema")
public class UserModel {

    private static final Long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Schema(description = "ID do usuário", example = "b3b1c2d3-4e5f-6789-0123-456789abcdef")
   private UUID userId;

   @Schema(description = "Nome do usuário", example = "João Silva")
   private String name;

   @Schema(description = "E-mail do usuário", example = "joao@email.com")
   private String email;

}
