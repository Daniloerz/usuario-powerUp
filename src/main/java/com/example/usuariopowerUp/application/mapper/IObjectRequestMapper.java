package com.example.usuariopowerUp.application.mapper;

import com.example.usuariopowerUp.application.dto.request.ObjectRequestDto;
import com.example.usuariopowerUp.domain.model.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectRequestMapper {


    ObjectModel toObject(ObjectRequestDto objectRequestDto);
}
