package br.com.cruz.vita.usuario.dto;

import java.time.LocalDateTime;

import br.com.cruz.vita.usuario.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	private String email;
	private String senha;
	private String cpf;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	// private LocalDateTime data_exclusao;
	public void setDataDeCadastro(LocalDateTime now) {

	}

	public static int getTentativasDeLogin() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static boolean isBloqueado() {
		// TODO Auto-generated method stub
		return false;
	}

	public UsuarioModel get() {
		// TODO Auto-generated method stub
		return null;
	}

}
