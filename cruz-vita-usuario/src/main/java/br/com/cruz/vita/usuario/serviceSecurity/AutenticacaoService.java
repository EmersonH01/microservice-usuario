package br.com.cruz.vita.usuario.serviceSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import br.com.cruz.vita.usuario.model.StatusUsuarioEnum;
import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.CadastroUsuarioRepository;

@Service
public class AutenticacaoService {

	@Autowired
	public CadastroUsuarioRepository cadastroRepository;

	// Busca o usuário pelo email e senha 
	public String autenticar (UsuarioModel usuario ) {
		

		if (cadastroRepository.findByEmail(usuario.getEmail()).isPresent()) {
			UsuarioModel usuarioBanco = cadastroRepository.findByEmail(usuario.getEmail()).get();
			UsuarioModel usuarioModel = usuario;
			usuarioModel.setId(usuarioBanco.getId());
			usuarioModel.setStatus(usuarioBanco.getStatus());
			
			if (usuarioModel.getSenha().equals(usuarioBanco.getSenha())) {
				usuarioModel.setNumTentativasFalhas(0);
				usuarioModel.setId(usuarioBanco.getId());
				usuarioModel.setStatus(usuarioBanco.getStatus());
				usuarioModel.setSenha(usuarioBanco.getSenha());

				cadastroRepository.save(usuarioModel);
				return "Usuário autenticado com sucesso!";
				
			} else {
				usuarioBanco = cadastroRepository.findByEmail(usuario.getEmail()).get();
				int tentativasFalhas = usuarioBanco.getNumTentativasFalhas();
				usuarioModel.setNumTentativasFalhas(tentativasFalhas + 1);
				usuarioModel.setStatus(usuarioBanco.getStatus());

				cadastroRepository.save(usuarioModel);
				if (tentativasFalhas >= 5) {
					usuarioModel.setStatus(StatusUsuarioEnum.bloqueado);
					cadastroRepository.save(usuarioModel);
					return "Usuário bloqueado por muitas tentativas de login!";
				}
				return "Senha incorreta! Tentativa " + (tentativasFalhas) + " de 5.";
			}
		}		 
		return "Email não existe na nossa base de dados";
	}
}
