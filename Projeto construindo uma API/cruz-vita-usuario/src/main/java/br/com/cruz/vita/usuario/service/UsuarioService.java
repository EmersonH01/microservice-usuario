package br.com.cruz.vita.usuario.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import br.com.cruz.vita.usuario.dto.UsuarioDesativadoDto;
import br.com.cruz.vita.usuario.dto.UsuariosAtivosDTO;
import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.CadastroUsuarioRepository;
import lombok.Data;

@Service
@Data
public class UsuarioService {

	@Autowired
	public CadastroUsuarioRepository cadastroRepository;

	private static final String SUCESSO = "SUCESSO";

	@Autowired
	private ModelMapper modelMapper;
	
	public void realizaConection(UsuarioDTO usuaDto) {
		RestTemplate realizaConexao = new RestTemplate();
		realizaConexao.getForObject("http://localhost:8888", realizaConexao.getClass());
				
	}
	
	public String criaUsuarioNovo(UsuarioDTO usuarioDto) {
		String mensagemValidacao = validaCampos(usuarioDto);
		if (!mensagemValidacao.equals(SUCESSO)) {
			return mensagemValidacao;
		}
		if (verificaCPFexistente(usuarioDto) || verificaSeUsuarioExistePeloEmail(usuarioDto.getEmail())) {
			return "Este usuario já esta cadastrado no banco de dados";
		}else {
			CadastroDeUmNovoUsuario(usuarioDto);
			return "usuario foi cadastrado com sucesso";
		}
	}

	private String validaCampos(UsuarioDTO usuarioDto) {
		if (verificaCampoEmailVazio(usuarioDto)) {
			return "O campo de email esta vazio";
		} else if (verificaCampoCPFVazio(usuarioDto)) {
			return "Campo de CPF esta vazio";
		}
		return SUCESSO;
	}

	private void CadastroDeUmNovoUsuario(UsuarioDTO usuarioDto) {
		ModelMapper mapper = new ModelMapper();
		UsuarioModel usuario = mapper.map(usuarioDto, UsuarioModel.class);
		usuario.setDataDeCadastro(LocalDateTime.now());
		cadastroRepository.save(usuario);
	}

	public String editarUsuario(UsuarioDTO usuarioDto, String email) {
		try {
			ModelMapper mapper = new ModelMapper();
			UsuarioModel editar = cadastroRepository.findByEmail(email).get();
			mapper.map(usuarioDto, editar);
			if (verificaSeUsuarioExistePeloEmail(usuarioDto.getEmail())) {
				return "Email de atualização já existe";
			} else {

				cadastroRepository.save(editar);
			}

			return "Informações atualizadas com sucesso, Email: " + usuarioDto.getEmail() + ", CPF: " + usuarioDto.getCpf() + ", Senha: "
					+ usuarioDto.getSenha();
		} catch (Exception e) {
			return "Email não encontrado";
		}

	}

	public List<UsuarioDTO> ListaUsuario() {
		List<UsuarioModel> lista = cadastroRepository.findAll();
		List<UsuarioDTO> listaResponse = lista.stream().map(user -> modelMapper.map(user, UsuarioDTO.class))
				.collect(Collectors.toList());
		return listaResponse;
	}

	public String ExcluirPorEmail(String email) {
		UsuarioModel busca = cadastroRepository.findByEmail(email).get();
		busca.setData_exclusao(LocalDateTime.now());
		cadastroRepository.save(busca);
		return "Usuario Deletado com sucesso";
	}

	public String buscaPorEmail(String email) {
		UsuarioModel novaBusca = cadastroRepository.findByEmail(email).get();
		String BuscaCpf = novaBusca.getCpf();
		novaBusca.setDataDeCadastro(LocalDateTime.now());
		return " Email vinculado ao CPF " + BuscaCpf;
	}

	public ResponseEntity<String> CriarLoteUsuario(List<UsuarioDTO> usuarios) {
		try {
			List<UsuarioModel> lista = new ArrayList<>();
			for (UsuarioDTO usuario : usuarios) {
				lista.add(new UsuarioModel(usuario));
			}
			cadastroRepository.saveAll(lista);
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuarios cadastrados com sucesso");
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Este usuario ja existe");
		}
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

	private Boolean verificaSeUsuarioExistePeloEmail(String email) {
		if (cadastroRepository.findByEmail(email).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean verificaCPFexistente(UsuarioDTO usuarioDTO) {
		if (cadastroRepository.findByCPF(usuarioDTO.getCpf()).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificaCampoEmailVazio(UsuarioDTO usuarioDTO) {
		if (usuarioDTO.getEmail().isEmpty() || usuarioDTO.getEmail() == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificaCampoCPFVazio(UsuarioDTO usuarioDTO) {
		if (usuarioDTO.getCpf().isEmpty() || usuarioDTO.getCpf() == null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificaCampoSenhaVazio(UsuarioDTO usuarioDTO) {
		if (usuarioDTO.getSenha().isEmpty() || usuarioDTO.getSenha() == null) {
			return true;
		} else {
			return false;
		}
	}

}
