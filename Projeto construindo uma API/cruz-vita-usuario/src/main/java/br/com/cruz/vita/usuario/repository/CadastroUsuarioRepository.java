package br.com.cruz.vita.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.common.base.Optional;

import br.com.cruz.vita.usuario.model.UsuarioModel;

public interface CadastroUsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	
	  @Query(value = " SELECT * FROM usuarios WHERE usuarios.usuario = :usuario", nativeQuery = true)
	  Optional<UsuarioModel> findByEmail(@Param("usuario") String email);
	  
	  @Query(value = " SELECT * FROM usuarios WHERE usuarios.usuario = :usuario", nativeQuery = true)
	  Optional<UsuarioModel> findByCPF(@Param("usuario") String cpf);
	   
	  @Query(value = " SELECT * FROM usuarios WHERE data_exclusao is not null ", nativeQuery = true)
	  List<UsuarioModel> buscaDesativados();
	  
	  @Query(value = "SELECT * FROM usuarios WHERE data_exclusao is null", nativeQuery = true)
	  List<UsuarioModel> buscaAtivados();
	  
	  UsuarioModel findBySenha(String senha);  
	  UsuarioModel deleteByEmail(String email);
}