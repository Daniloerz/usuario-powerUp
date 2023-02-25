package com.example.usuariopowerUp.domain.api;


import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.model.UsuarioModel;

public interface IUsuarioServicePort {

    void saveUsuario(UsuarioModel usuarioModel, String role);

    UsuarioResponseDto findUserByIdSP(Integer id);
}