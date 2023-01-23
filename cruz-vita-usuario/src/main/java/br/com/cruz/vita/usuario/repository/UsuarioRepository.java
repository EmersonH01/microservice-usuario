package br.com.cruz.vita.usuario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.cruz.vita.usuario.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long>{

	UsuarioModel findByEmail(String email);

	
}
