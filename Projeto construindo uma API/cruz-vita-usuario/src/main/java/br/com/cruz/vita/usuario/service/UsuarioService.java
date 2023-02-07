package br.com.cruz.vita.usuario.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import br.com.cruz.vita.usuario.dto.UsuarioDesativadoDto;
import br.com.cruz.vita.usuario.dto.UsuariosAtivosDTO;
import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.CadastroUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	public CadastroUsuarioRepository cadastroRepository;

	@Autowired
	private ModelMapper modelMapper;

	public void criaUsuarioNovo(UsuarioDTO usuarioDto) {
		ModelMapper mapper = new ModelMapper();
		UsuarioModel usuario = mapper.map(usuarioDto, UsuarioModel.class);
		usuario.setDataDeCadastro(LocalDateTime.now());
		cadastroRepository.save(usuario);
	  }


	public UsuarioDTO editaUsuario(UsuarioDTO usuarioDto, String email) {
		UsuarioModel editar = cadastroRepository.findByEmail(email);
		editarUsuario(usuarioDto, editar);
		cadastroRepository.save(editar);
		ModelMapper mapper = new ModelMapper();
		return mapper.map(editar, UsuarioDTO.class);
	  }
	
	 // Editar senha
	public String editarUsuario(UsuarioDTO usuarioSenha, String senha) {
		UsuarioModel editar = cadastroRepository.findBySenha(senha);
		editarUsuario(usuarioSenha, editar);
		cadastroRepository.save(editar);
		return "sua senha foi alterada com sucesso";
	}

	private void editarUsuario(UsuarioDTO usuarioDto, UsuarioModel editar) {
		editar.setEmail(usuarioDto.getEmail());
		editar.setSenha(usuarioDto.getSenha());
	}
	
	public List<UsuarioDTO> ListaUsuario() {
		List<UsuarioModel> lista = cadastroRepository.findAll();
		List<UsuarioDTO> listaResponse = lista.stream().map(user -> modelMapper.map(user, UsuarioDTO.class))
				.collect(Collectors.toList());
		return listaResponse;
	}

	public String ExcluirPorEmail(String email) {
		UsuarioModel busca = cadastroRepository.findByEmail(email);
		busca.setData_exclusao(LocalDateTime.now());
		cadastroRepository.save(busca);
		return "Usuario Deletado com sucesso";
	}

	public String buscaPorEmail(String email) {
		UsuarioModel novaBusca = cadastroRepository.findByEmail(email);
		String BuscaCpf = novaBusca.getCpf();
		novaBusca.setDataDeCadastro(LocalDateTime.now());
		return " Email vinculado ao CPF " + BuscaCpf;
	}

	public String CriarLoteUsuario(List<UsuarioDTO> usuarios) {
		List<UsuarioModel> lista = new ArrayList<>();
		for (UsuarioDTO usuario : usuarios) {
			lista.add(new UsuarioModel(usuario));
		}
		cadastroRepository.saveAll(lista);
		return "Lote cadastrado com sucesso" ;
	}

	public List<UsuarioDesativadoDto> buscarPorDesativados() {
		List<UsuarioModel> lista = cadastroRepository.buscaDesativados();
		List<UsuarioDesativadoDto> listaResponse = lista.stream()
				.map(user -> modelMapper.map(user, UsuarioDesativadoDto.class)).collect(Collectors.toList());
		return listaResponse;
	}

	public List<UsuariosAtivosDTO> buscarPorAtivados() {
		List<UsuarioModel> lista = cadastroRepository.buscaAtivados();
		List<UsuariosAtivosDTO> listaResponse = lista.stream()
				.map(user -> modelMapper.map(user, UsuariosAtivosDTO.class)).collect(Collectors.toList());
		return listaResponse;
	}
}
