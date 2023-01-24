package br.com.cruz.vita.usuario.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, name = "usuario")
	@NotNull
	private String email;
	@NotNull
	private String senha;
	@NotNull
	private String cpf;
	
	@Column(name = "tentativa_login")
	private Integer tentativaLogin;
	
	@Column(name = "data_inclusao")
	private LocalDateTime dataInclusao;
	
	@Column(name = "data_ultimo_login")
	private LocalDateTime dataUltimoLogin;
	
	@Column(name = "data_exclusao")
	private LocalDateTime dataExclusao;
	
	@Column
	private Boolean bloqueado;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long idPessoa;


	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * @Column(name = "id_pessoa") private Long idPessoa;
	 * 
	 * 
	 * private????tentativaLogin; private????data_inclusap; private????bloquado;
	 * private????dataUltimoLogin; private????dataExclusaoa;
	 */
}
