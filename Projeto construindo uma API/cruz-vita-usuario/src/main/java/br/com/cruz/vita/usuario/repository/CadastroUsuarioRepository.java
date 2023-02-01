package br.com.cruz.vita.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cruz.vita.usuario.model.UsuarioModel;

public interface CadastroUsuarioRepository extends JpaRepository<UsuarioModel, Long>{
	    @Query(
	 value = "SELECT * FROM usuarios u WHERE u.status = :status AND u.email = :email", 
	  nativeQuery = true)
	UsuarioModel findActiveUserByEmail(@Param(value = "status") String status, @Param(value = "email") String email);
	    
	    //Busca no Banco de dados 
	  UsuarioModel findByEmail(String email);

	  UsuarioModel deleteByEmail(String email);

	  String save(List<UsuarioModel> lista); 
	    
	
}
