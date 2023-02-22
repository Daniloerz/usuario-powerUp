package com.example.usuariopowerUp.application.handler;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.model.UsuarioModel;

public interface IUsuarioHandler {
    void saveUsuarioPropietario(UsuarioRequestDto userRequestDto);

    UsuarioResponseDto findUserById(Integer id);
}