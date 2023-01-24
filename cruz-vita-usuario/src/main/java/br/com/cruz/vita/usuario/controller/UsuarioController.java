package br.com.cruz.vita.usuario.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private UsuarioService usuarioService;

	@GetMapping("/listar")
	public ResponseEntity<List<UsuarioModel>> listarUsuarios() {

		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuario());
	}

	@GetMapping("/buscar/{email}")
	public ResponseEntity<String> buscarPorEmail(@PathVariable String email) {

		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarPorEmail(email));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuario) {

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}

	@PostMapping("/criarlote")
	public ResponseEntity<String> criarUsuarioLote(@RequestBody List<UsuarioDTO> usuario) {

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarPorLote(usuario));
	}

	@DeleteMapping("/deletar/{email}")
	public ResponseEntity<String> deletarEmail(@PathVariable String email) {

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(usuarioService.deletarPorEmail(email));
	}

	@PutMapping("/atualizar/{email}")
	public ResponseEntity<String> atualizarUsuario(@RequestBody UsuarioDTO usuario, @PathVariable String email) {

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.atualizarViaEmail(usuario, email));
	}

}
