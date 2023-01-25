package br.com.cruz.vita.usuario.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cruz.vita.usuario.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	UsuarioModel findByEmail(String email);

	List<UsuarioModel> findByDataExclusao(LocalDateTime dataExclusao);

	
}
