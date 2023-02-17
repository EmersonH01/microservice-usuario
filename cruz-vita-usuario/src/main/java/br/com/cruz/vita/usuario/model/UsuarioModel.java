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
import lombok.NoArgsConstructor;

@Valid
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
		
	@Column(name = "senha", length = 10)
	private String senha; 
	
	private LocalDateTime data_exclusao;
	
	@Column(name = "data_cadastro")
	private LocalDateTime dataDeCadastro = LocalDateTime.now();
		
	@Column(name = "cpf")
	@CPF
	private String cpf ;
	
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public LocalDateTime getData_exclusao() {
		return data_exclusao;
	}



	public void setData_exclusao(LocalDateTime data_exclusao) {
		this.data_exclusao = data_exclusao;
	}



	public LocalDateTime getDataDeCadastro() {
		return dataDeCadastro;
	}



	public void setDataDeCadastro(LocalDateTime dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public UsuarioModel(UsuarioDTO usuario){
    	this.cpf = usuario.getCpf();
    	this.email = usuario.getEmail();
    	this.senha = usuario.getSenha();
     }
    
    
    
}
