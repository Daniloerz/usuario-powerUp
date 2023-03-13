package com.example.usuariopowerUp.infrastructure.out.jpa.adapter;

import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.UsuarioEntity;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IUsuarioEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IUsuarioRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioJpaAdapterTest {

    private IUsuarioRepository usuarioRepository = Mockito.mock(IUsuarioRepository.class);
    private IUsuarioEntityMapper usuarioEntityMapper = Mockito.mock(IUsuarioEntityMapper.class);

    private UsuarioJpaAdapter usuarioJpaAdapter = new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);

    @Test
    void saveUsuario() {
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirezhotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setClave("1234");
        usuarioEntity.setApellido("Ramirez");
        usuarioEntity.setCelular("3165365352");
        usuarioEntity.setCorreo("danilo.ramirezhotmail.com");
        usuarioEntity.setDocumento("1144071226");
        usuarioEntity.setNombre("Danilo");

        Mockito.when(usuarioEntityMapper.toEntity(ArgumentMatchers.any(UsuarioModel.class))).thenReturn(usuarioEntity);
        Mockito.when(usuarioRepository.save(ArgumentMatchers.any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        Mockito.when(usuarioEntityMapper.toUsuarioModel(ArgumentMatchers.any(UsuarioEntity.class)))
                .thenReturn(usuarioModel);

        UsuarioModel usuarioModelResponse = usuarioJpaAdapter.saveUsuario(usuarioModel);

        assertEquals(usuarioModel.getCorreo(), usuarioModelResponse.getCorreo());
        assertEquals(usuarioModel.getCelular(), usuarioModelResponse.getCelular());
        assertEquals(usuarioModel.getApellido(), usuarioModelResponse.getApellido());
        assertEquals(usuarioModel.getDocumento(), usuarioModelResponse.getDocumento());

    }

    @Test
    void findUserByIdPP() {
    }
}