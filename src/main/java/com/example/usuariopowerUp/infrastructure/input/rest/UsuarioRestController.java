package com.example.usuariopowerUp.infrastructure.input.rest;


import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final IUsuarioHandler usuarioHandler;
    private final String rolePropietario = "propietario";
    private final String roleEmpleado = "empleado";
    private final String roleCliente = "cliente";

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })

    @PostMapping("/create-owner")
    public ResponseEntity<Void> createOwnerUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            usuarioHandler.saveUsuario(usuarioRequestDto, rolePropietario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error creando propietario " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-employe")
    public ResponseEntity<Void> createEmployeUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            usuarioHandler.saveUsuario(usuarioRequestDto, roleEmpleado);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error creando empleado " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-client")
    public ResponseEntity<Void> createClientUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            usuarioHandler.saveUsuario(usuarioRequestDto, roleCliente);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error creando cliente " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findUsuarioById(@PathVariable Integer id){

        return ResponseEntity.ok(usuarioHandler.findUserById(id));
    }

}