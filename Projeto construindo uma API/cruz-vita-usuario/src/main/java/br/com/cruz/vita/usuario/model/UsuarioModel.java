package br.com.cruz.vita.usuario.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "usuarios")
public class UsuarioModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id ; 
		
	@Column(name = "usuario" ,unique = true , nullable = false)
	@Email 
	private String email;
	
	@Column(name = "Senha", length = 10)
	private String senha; 
	
	@Column(name = "DataExclusão")
	private LocalDateTime dataUsuarioDeletado ;
	
	@Column(name = "DataCadastro")
	private LocalDateTime dataDeCadastro;
	
	@Column(name = "CPF")
	private String cpf ;
	
    public UsuarioModel(UsuarioDTO usuario){
    	this.cpf = usuario.getCpf();
    	this.email = usuario.getEmail();
    	this.senha = usuario.getSenha();
      }	
}
