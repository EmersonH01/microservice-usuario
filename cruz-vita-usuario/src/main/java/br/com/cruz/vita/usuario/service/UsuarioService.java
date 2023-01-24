package br.com.cruz.vita.usuario.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<UsuarioModel> listarUsuario() {
		List<UsuarioModel> lista = usuarioRepository.findAll();
		return lista;
	}

	public String buscarPorEmail(String email) {
		UsuarioModel buscarEmail = usuarioRepository.findByEmail(email);
		String cpfUsuario = buscarEmail.getCpf();

		return "Email possui cadastro vinculado com o cpf: " + cpfUsuario;
	}
	
	public String cadastrarUsuario(UsuarioDTO usuario) {
		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);

		return "usuário criado com sucesso!";
	}
	
	
	public String cadastrarPorLote(List<UsuarioDTO> usuario) {
		for (UsuarioDTO itemLista : usuario) {
			UsuarioModel usuarioModel = modelMapper.map(itemLista, UsuarioModel.class);

			usuarioRepository.save(usuarioModel);
		}
		return "Lote cadastrado com sucesso";
	}
	
	
	public String atualizarViaEmail(UsuarioDTO usuario, String email) {
		UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
		usuarioModel.setId(buscaEmail.getId());
		String cpfUsuario = usuarioModel.getCpf();
		usuarioRepository.save(usuarioModel);
		
		return "Usuário vinculado ao cpf " + cpfUsuario + " atualizado com sucesso.";
	}
	
	public String deletarPorEmail(String email) {
		UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
		String cpfUsuario = buscaEmail.getCpf();
		usuarioRepository.delete(buscaEmail);
		return "usuário vinculado ao cpf " + cpfUsuario + " deletado com sucesso";
	}

}
