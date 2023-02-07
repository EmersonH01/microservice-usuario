package br.com.cruz.vita.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cruz.vita.usuario.model.UsuarioModel;

public interface CadastroUsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    
	   //Busca no Banco de dados 
	  UsuarioModel findByEmail(String email);
	  UsuarioModel findBySenha(String senha);
	  
	  UsuarioModel deleteByEmail(String email);

	  @Query(value = " SELECT * FROM usuarios WHERE data_exclusao is not null ", nativeQuery = true)
	  List<UsuarioModel> buscaDesativados();
	  
	  @Query(value = "SELECT * FROM usuarios WHERE data_exclusao is null", nativeQuery = true)
	  List<UsuarioModel> buscaAtivados();
	  
}