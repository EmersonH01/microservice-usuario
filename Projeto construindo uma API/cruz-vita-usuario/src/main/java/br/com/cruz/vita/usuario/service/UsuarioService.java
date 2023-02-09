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
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

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
	
	public String criaUsuarioNovo(UsuarioDTO usuarioDto){	
		if(verificaCPFexistente(usuarioDto) || verificaSeUsuarioExistePeloEmail(usuarioDto)) {
			return "Este usuario já esta cadastrado no banco de dados" ;
		}else {
			ModelMapper mapper = new ModelMapper();
			UsuarioModel usuario = mapper.map(usuarioDto, UsuarioModel.class);
			usuario.setDataDeCadastro(LocalDateTime.now());
			cadastroRepository.save(usuario);
			return "usuario foi cadastrado com sucesso" ;
		 } 					
		}
 
	
	
	public UsuarioDTO editaUsuario(UsuarioDTO usuarioDto, String email) {
		UsuarioModel editar = cadastroRepository.findByEmail(email).get();
		editarUsuario(usuarioDto, editar);
		cadastroRepository.save(editar);
		ModelMapper mapper = new ModelMapper();
		return mapper.map(editar, UsuarioDTO.class);
	  }
	
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
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuarios cadastrados com sucesso") ;
			
		}catch (DataIntegrityViolationException e) {
			return  ResponseEntity.status(HttpStatus.CREATED).body("Nosso banco contem um email com usuario cadastrado") ;
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
	

	private Boolean verificaSeUsuarioExistePeloEmail(UsuarioDTO usuaDto) {
		if(cadastroRepository.findByEmail(usuaDto.getEmail()).isPresent()) {
			return true ;		
		 }else {
			 return false;
			 }
	     }
	
	  private Boolean verificaCPFexistente(UsuarioDTO usuarioDTO) {
		  if(cadastroRepository.findByCPF(usuarioDTO.getCpf()).isPresent()) {
			  return true ;
		  }else {
			  return false ;
		  }
	  }
	
}
