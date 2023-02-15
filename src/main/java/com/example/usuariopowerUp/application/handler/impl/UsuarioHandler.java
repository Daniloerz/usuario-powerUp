package com.example.usuariopowerUp.application.handler.impl;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import com.example.usuariopowerUp.application.mapper.IUsuarioRequestMapper;
import com.example.usuariopowerUp.domain.api.IUsuarioServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioHandler implements IUsuarioHandler {
    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioRequestMapper usuarioRequestMapper;

    @Override
    public void saveUsuarioPropietario(UsuarioRequestDto userRequestDto) {
        usuarioServicePort.saveUsuarioPropietario(usuarioRequestMapper.toObject(userRequestDto));
    }
}
