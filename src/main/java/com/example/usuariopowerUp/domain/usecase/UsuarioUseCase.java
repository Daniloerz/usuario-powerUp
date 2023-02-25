package com.example.usuariopowerUp.domain.usecase;

import com.example.usuariopowerUp.application.dto.response.RoleResponseDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.api.IUsuarioServicePort;
import com.example.usuariopowerUp.domain.exception.ValidationException;
import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.domain.spi.IRolePersistencePort;
import com.example.usuariopowerUp.domain.spi.IUsuarioPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

public class UsuarioUseCase implements IUsuarioServicePort {

    public final Logger log = LoggerFactory.getLogger(UsuarioUseCase.class);

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final PasswordEncoder passwordEncoder;


    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IRolePersistencePort rolePersistencePort, PasswordEncoder passwordEncoder) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void saveUsuario(UsuarioModel usuarioModel, String role) {
        this.validateUsuario(usuarioModel);

        RoleModel userRole = rolePersistencePort.findByNombre(role);

        usuarioModel.setIdRole(userRole.getId());

        usuarioModel.setClave(passwordEncoder.encode(usuarioModel.getClave()));

        usuarioPersistencePort.saveUsuario(usuarioModel);
    }

    @Override
    public UsuarioResponseDto findUserByIdSP(Integer id) {
        UsuarioModel usuarioModel = usuarioPersistencePort.findUserByIdPP(id);
        RoleModel roleModel = rolePersistencePort.findRoleByIdPP(usuarioModel.getIdRole());

        return buildUsuarioResponseDto(usuarioModel, roleModel);
    }

    private UsuarioResponseDto buildUsuarioResponseDto(UsuarioModel usuarioModel, RoleModel roleModel){
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
        RoleResponseDto roleResponseDto = new RoleResponseDto();

        roleResponseDto.setId(roleModel.getId());
        roleResponseDto.setNombre(roleModel.getNombre());
        roleResponseDto.setDescripcion(roleModel.getDescripcion());

        usuarioResponseDto.setRole(roleResponseDto);

        usuarioResponseDto.setId(usuarioModel.getId());
        usuarioResponseDto.setApellido(usuarioModel.getApellido());
        usuarioResponseDto.setDocumento(usuarioModel.getDocumento());
        usuarioResponseDto.setCelular(usuarioModel.getCelular());
        usuarioResponseDto.setCorreo(usuarioModel.getCorreo());
        usuarioResponseDto.setNombre(usuarioModel.getNombre());

        return usuarioResponseDto;
    }


    private void validateUsuario(UsuarioModel usuarioModel) {
        this.validateCorreo(usuarioModel.getCorreo());
        this.validateDocumento(usuarioModel.getDocumento());
        this.validateCelular(usuarioModel.getCelular());
    }

    private void validateCorreo(String correo){
        String regex = "^(.+)@(.+)$";

        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(correo).matches()){
            log.error("Correo invalido");
            throw new ValidationException("Correo invalido");
        }
    }


    private void validateCelular(String celular){
        String regex = "[+]?[0-9]{10,12}";

        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(celular).matches()){
            log.error("Celular invalido");
            throw new ValidationException("Celular invalido");
        }
    }   private void validateDocumento(String documento){
        String regex = "[0-9]+";

        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(documento).matches()){
            log.error("Documento invalido");
            throw new ValidationException("Documento invalido");
        }
    }
}