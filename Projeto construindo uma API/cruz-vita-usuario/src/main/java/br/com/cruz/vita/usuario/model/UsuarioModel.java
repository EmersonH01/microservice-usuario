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

import org.hibernate.validator.constraints.br.CPF;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Valid
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table (name = "usuarios")
public class UsuarioModel {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id ; 
		
	@Column(name = "usuario" ,unique = true , nullable = false)
	@Email 
	private String email;
	
	@Column(name = "senha", length = 10)
	private String senha; 
	
	private LocalDateTime data_exclusao;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataDeCadastro = LocalDateTime.now();
	
	@Column(name = "cpf")
	private String cpf ;
	
    public UsuarioModel(UsuarioDTO usuario){
    	this.cpf = usuario.getCpf();
    	this.email = usuario.getEmail();
    	this.senha = usuario.getSenha();
     }

//	public void setData_exclusao(LocalDateTime now) {
//		// TODO Auto-generated method stub
//		
//	}    
}
