package br.com.cruz.vita.usuario.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/buscar")
	public List<UsuarioModel> listaUsuarios(){
		return (List<UsuarioModel>) usuarioRepository.findAll();
	}
	
	@PostMapping("/cadastrar")
	public UsuarioModel criarUsuario(@RequestBody UsuarioDTO usuario){
		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);
		return usuarioNovo;
	}
	
	@PutMapping("/atualizar")
	public UsuarioModel atualizarUsuario (@RequestBody UsuarioDTO usuario) {
		
		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);
		return usuarioNovo;
		
	}
	
	@DeleteMapping("deletar")
	public Optional<UsuarioModel> excluirUsuario(@PathVariable Long id) {
		Optional<UsuarioModel>  usuario = usuarioRepository.findById(id);
		usuarioRepository.deleteById(id);
		return usuario;
	}
	
	
}
