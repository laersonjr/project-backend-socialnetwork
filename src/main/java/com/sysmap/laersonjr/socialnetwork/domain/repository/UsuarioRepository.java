package com.sysmap.laersonjr.socialnetwork.domain.repository;

import com.sysmap.laersonjr.socialnetwork.domain.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;
public interface UsuarioRepository extends MongoRepository<Usuario, UUID> {
}
