package br.com.cruz.vita.usuario.dto;


import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	private String cpf;
	
	//private LocalDateTime data_exclusao;
	public void setDataDeCadastro(LocalDateTime now) {
		
  }
	
}
