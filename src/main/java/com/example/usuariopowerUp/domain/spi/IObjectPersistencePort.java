package com.example.usuariopowerUp.domain.spi;

import com.example.usuariopowerUp.domain.model.ObjectModel;

import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}