package com.example.usuariopowerUp.infrastructure.input.rest;


import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import com.example.usuariopowerUp.infrastructure.input.enums.RoleEnum;
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


    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })

    @PostMapping("/{idAdmin}/propietario")
    public ResponseEntity<Void> createOwnerUser(@PathVariable Integer idAdmin,
                                                @RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            usuarioHandler.savePropietario(idAdmin, usuarioRequestDto, RoleEnum.PROPIETARIO.getDbName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creando propietario " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idPropietario}/empleado")
    public ResponseEntity<Void> createEmployeeUser(@PathVariable Integer idPropietario,
                                                  @RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            usuarioHandler.saveEmpleado(idPropietario, usuarioRequestDto, RoleEnum.EMPLEADO.getDbName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error creando empleado " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Void> createClientUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        try {
            usuarioHandler.saveCliente(usuarioRequestDto, RoleEnum.CLIENTE.getDbName());
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