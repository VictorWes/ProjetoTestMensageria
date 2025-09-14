package dev.java10x.user.controller;

import dev.java10x.user.domain.UserModel;
import dev.java10x.user.dto.UserDto;
import dev.java10x.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Cria um novo usuário", description = "Cria um usuário e publica um evento de boas-vindas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserModel> criarUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Dados do usuário a ser criado",
                required = true,
                content = @Content(schema = @Schema(implementation = UserDto.class))
            )
            @RequestBody UserDto userDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveAndPublish(userModel));
    }

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModel.class)))
    })
    @GetMapping("list/users")
    public ResponseEntity<List<UserModel>> listarTodosUsuarios() {
        List<UserModel> usuarios = userService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }




}
