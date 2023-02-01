package br.com.cruz.vita.usuario.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.CadastroUsuarioRepository;
import lombok.Data;

@Service
@Data
public class UsuarioService {

	@Autowired
	public CadastroUsuarioRepository cadastro;

	public void criaUsuarioNovo(UsuarioDTO usuarioDto) {
		ModelMapper mapper = new ModelMapper();
		UsuarioModel usuario = mapper.map(usuarioDto, UsuarioModel.class);
		usuario.setDataDeCadastro(LocalDateTime.now());
		cadastro.save(usuario);
	}

	public UsuarioDTO editaUsuario(UsuarioDTO usuarioDto, String email) {
		UsuarioModel editar = cadastro.findByEmail(email);
		editarUsuario(usuarioDto, editar);
		cadastro.save(editar);
		ModelMapper mapper = new ModelMapper();
		return mapper.map(editar, UsuarioDTO.class);
	}

	private void editarUsuario(UsuarioDTO usuarioDto, UsuarioModel editar) {
		editar.setEmail(usuarioDto.getEmail());
		editar.setSenha(usuarioDto.getSenha());
	}

	public List<UsuarioModel> ListaUsuario() {
		List<UsuarioModel> lista = cadastro.findAll();
		return lista;
	}
	
	public String DeletaUsuario(Long id) {
		cadastro.deleteById(id);
		return "usuario deletado";
	}

	public String ExcluirPorEmail(String email) {
		UsuarioModel busca = cadastro.findByEmail(email);
		busca.setDataUsuarioDeletado(LocalDateTime.now());	
		cadastro.save(busca);
		return "Usuario Deletado com sucesso";
	 }

	public String BuscaPorEmail(String email) {
		UsuarioModel novaBusca = cadastro.findByEmail(email);
		String BuscaCpf = novaBusca.getCpf();
		return " Email vinculado ao CPF " + BuscaCpf;
	}

	public String CriarLoteUsuario(List<UsuarioDTO> usuarios) {
		List<UsuarioModel> lista = new ArrayList<>();
		for (UsuarioDTO usuario : usuarios) {
			  lista.add(new UsuarioModel(usuario));
		   }
		cadastro.saveAll(lista);
	  return "Lote cadastrado com sucesso";
	}
	
	
}
