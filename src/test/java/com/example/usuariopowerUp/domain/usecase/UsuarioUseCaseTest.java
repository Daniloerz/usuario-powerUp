package com.example.usuariopowerUp.domain.usecase;

import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.exception.ValidationException;
import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.domain.spi.IRolePersistencePort;
import com.example.usuariopowerUp.domain.spi.IUsuarioPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioUseCaseTest {

    private final IUsuarioPersistencePort usuarioPersistencePort = Mockito.mock(IUsuarioPersistencePort.class);
    private final IRolePersistencePort rolePersistencePort = Mockito.mock(IRolePersistencePort.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    private final UsuarioUseCase usuarioUseCase = new UsuarioUseCase(usuarioPersistencePort,
            rolePersistencePort,
            passwordEncoder);

    @Test
    void saveCliente() {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        RoleModel roleModel = new RoleModel(3, "cliente", "cliente");
        Mockito.when(rolePersistencePort.findByNombre(ArgumentMatchers.any())).thenReturn(roleModel);
        Mockito.when(passwordEncoder.encode(ArgumentMatchers.any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(ArgumentMatchers.any())).thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioUseCase.saveCliente(usuarioModel, "cliente"));

    }

    @Test
    void savePropietario(){

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        RoleModel roleModel = new RoleModel(1, "propietario", "propietario");
        Mockito.when(rolePersistencePort.findByNombre(ArgumentMatchers.any())).thenReturn(roleModel);
        RoleModel roleModelAdmin = new RoleModel(4, "administrador", "administrador");
        Mockito.when(rolePersistencePort.findRoleByIdPP(ArgumentMatchers.any())).thenReturn(roleModelAdmin);
        Mockito.when(passwordEncoder.encode(ArgumentMatchers.any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(ArgumentMatchers.any())).thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioUseCase.savePropietario(4, usuarioModel, "propietario"));
    }

    @Test
    void saveEmpleado(){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        RoleModel roleModel = new RoleModel(1, "empleado", "empleado");
        Mockito.when(rolePersistencePort.findByNombre(ArgumentMatchers.any())).thenReturn(roleModel);
        RoleModel roleModelPropietario = new RoleModel(1, "propietario", "propietario");
        Mockito.when(rolePersistencePort.findRoleByIdPP(ArgumentMatchers.any())).thenReturn(roleModelPropietario);
        Mockito.when(passwordEncoder.encode(ArgumentMatchers.any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(ArgumentMatchers.any())).thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioUseCase.saveEmpleado(1, usuarioModel, "empleado"));
    }

    @Test
    void saveUsuarioWithInvalidEmail() {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirezhotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        Mockito.when(usuarioPersistencePort.saveUsuario(ArgumentMatchers.any())).thenReturn(usuarioModel);

        ValidationException exception= assertThrows(ValidationException.class, () ->
                usuarioUseCase.saveCliente(usuarioModel, "propietario"));
        assertEquals("Correo invalido", exception.getMessage());
    }

    @Test
    void saveUsuarioWithInvalidCelular() {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("31653");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        Mockito.when(usuarioPersistencePort.saveUsuario(ArgumentMatchers.any())).thenReturn(usuarioModel);

        ValidationException exception= assertThrows(ValidationException.class, () ->
                usuarioUseCase.saveCliente(usuarioModel, "cliente"));
        assertEquals("Celular invalido", exception.getMessage());
    }

    @Test
    void saveUsuarioWithInvalidDocumento() {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("114407k226");
        usuarioModel.setNombre("Danilo");

        Mockito.when(usuarioPersistencePort.saveUsuario(ArgumentMatchers.any())).thenReturn(usuarioModel);

        ValidationException exception= assertThrows(ValidationException.class, () ->
                usuarioUseCase.saveCliente(usuarioModel, "cliente"));
        assertEquals("Documento invalido", exception.getMessage());
    }

    @Test
    void findUserByIdSP() {
        Integer id = 1;

        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirezhotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");
        usuarioModel.setId(id);
        usuarioModel.setIdRole(id);

        RoleModel roleModel = new RoleModel(1, "propietario", "propietario");

        Mockito.when(usuarioPersistencePort.findUserByIdPP(ArgumentMatchers.anyInt())).thenReturn(usuarioModel);
        Mockito.when(rolePersistencePort.findRoleByIdPP(ArgumentMatchers.anyInt())).thenReturn(roleModel);

        UsuarioResponseDto usuarioResponseDto = usuarioUseCase.findUserByIdSP(id);

        assertEquals(usuarioModel.getApellido(), usuarioResponseDto.getApellido());
        assertEquals(usuarioModel.getCelular(), usuarioResponseDto.getCelular());
        assertEquals(usuarioModel.getCorreo(), usuarioResponseDto.getCorreo());
        assertEquals(roleModel.getNombre(), usuarioResponseDto.getRole().getNombre());
    }
}