package com.example.usuariopowerUp.application.handler.impl;

import com.example.usuariopowerUp.application.dto.request.ObjectRequestDto;
import com.example.usuariopowerUp.application.dto.response.ObjectResponseDto;
import com.example.usuariopowerUp.application.handler.IObjectHandler;
import com.example.usuariopowerUp.application.mapper.IObjectRequestMapper;
import com.example.usuariopowerUp.application.mapper.IObjectResponseMapper;
import com.example.usuariopowerUp.domain.api.IObjectServicePort;
import com.example.usuariopowerUp.domain.model.ObjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ObjectHandler implements IObjectHandler {

    private final IObjectServicePort objectServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveObject(ObjectRequestDto objectRequestDto) {
        ObjectModel objectModel = objectRequestMapper.toObject(objectRequestDto);
        objectServicePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectResponseDto> getAllObjects() {
        return objectResponseMapper.toResponseList(objectServicePort.getAllObjects());
    }
}