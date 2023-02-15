package com.example.usuariopowerUp.domain.api;

import com.example.usuariopowerUp.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}