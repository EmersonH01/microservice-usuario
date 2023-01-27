package br.com.cruz.vita.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cruz.vita.usuario.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	UsuarioModel findByEmail(String email);

	
	
	@Query(value = "SELECT * FROM usuario WHERE data_exclusao IS NOT NULL", nativeQuery = true)
	List<UsuarioModel> findByDataExclusao();
	
	
	@Query(value = "SELECT * FROM usuario WHERE data_exclusao IS NULL", nativeQuery = true)
	List<UsuarioModel> findByDataInclusao();

	@Query(value = "SELECT * FROM usuario WHERE :cpf", nativeQuery = true)
	Optional<UsuarioModel> findByCpf(@Param(value = "cpf") String cpf);

	
	
}
