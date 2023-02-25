package com.example.usuariopowerUp.application.handler;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;

public interface IUsuarioHandler {
    void saveUsuario(UsuarioRequestDto userRequestDto, String role);

    UsuarioResponseDto findUserById(Integer id);
}