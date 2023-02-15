package com.example.usuariopowerUp.application.handler;

import com.example.usuariopowerUp.application.dto.request.ObjectRequestDto;
import com.example.usuariopowerUp.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}