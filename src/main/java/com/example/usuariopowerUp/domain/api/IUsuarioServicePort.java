package com.example.usuariopowerUp.domain.api;


import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.model.UsuarioModel;

public interface IUsuarioServicePort {

    void saveUsuarioPropietario(UsuarioModel usuarioModel);

    UsuarioResponseDto findUserByIdSP(Integer id);
}