package com.example.usuariopowerUp.domain.usecase;

import com.example.usuariopowerUp.domain.api.IUsuarioServicePort;
import com.example.usuariopowerUp.domain.exception.ValidationException;
import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.domain.spi.IRolePersistencePort;
import com.example.usuariopowerUp.domain.spi.IUsuarioPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class UsuarioUseCase implements IUsuarioServicePort {

    public final Logger log = LoggerFactory.getLogger(UsuarioUseCase.class);

    private final IUsuarioPersistencePort usuarioPersistencePort;
    private final IRolePersistencePort rolePersistencePort;


    public UsuarioUseCase(IUsuarioPersistencePort usuarioPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveUsuarioPropietario(UsuarioModel usuarioModel) {
        this.validateUsuario(usuarioModel);

        RoleModel role = rolePersistencePort.findByNombre("propietario");

        usuarioModel.setIdRol(role.getId());

        usuarioPersistencePort.saveUsuario(usuarioModel);
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
        String regex = "/[+]?[0-9]{10}";

        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(celular).matches()){
            log.error("Celular invalido");
            throw new ValidationException("Celular invalido");
        }
    }   private void validateDocumento(String documento){
        String regex = "/[0-9]/gm";

        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(documento).matches()){
            log.error("Documento invalido");
            throw new ValidationException("Documento invalido");
        }
    }
}