package br.com.cruz.vita.usuario.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "usuarios")
@Data
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "usuario", unique = true, nullable = false)
	@Email
	private String email;

	@Column(name = "senha", length = 10)
	private String senha;
	

	private LocalDateTime data_exclusao;

	@Column(name = "data_cadastro")
	private LocalDateTime dataDeCadastro = LocalDateTime.now();
	@Column(name = "cpf")
	@CPF
	private String cpf;


	@Column
	private int NumTentativasFalhas;
	
	
	@Enumerated(EnumType.STRING)
	private StatusUsuarioEnum status;

	public UsuarioModel(UsuarioDTO usuario) {
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}

	public static boolean isPresent() {
		// TODO Auto-generated method stub
		return true;
	}

}
