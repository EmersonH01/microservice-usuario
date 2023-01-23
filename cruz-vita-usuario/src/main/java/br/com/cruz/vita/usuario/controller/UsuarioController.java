package br.com.cruz.vita.usuario.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import br.com.cruz.vita.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	private UsuarioService usuarioService;

	@GetMapping("/buscar")
	public ResponseEntity<String> listaUsuarios() {
		List<UsuarioModel> lista = usuarioService.listaUsuario();
		return ResponseEntity.status(200).body("");
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuario) {
		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);
		return ResponseEntity.status(201).body("usuário criado com sucesso!");
	}

	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> atualizarUsuario(@RequestBody UsuarioDTO usuario) {

		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);
		return ResponseEntity.status(201).body(usuarioNovo);

	}

	@DeleteMapping("deletar")
	public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		return ResponseEntity.status(204).build();
	}

}
