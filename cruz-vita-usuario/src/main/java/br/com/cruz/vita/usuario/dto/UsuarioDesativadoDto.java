package br.com.cruz.vita.usuario.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsuarioDesativadoDto {

	private String email;
	private String senha;
	private String cpf;
	private LocalDateTime data_exclusao;
	
}
