package br.com.cruz.vita.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
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
import br.com.cruz.vita.usuario.dto.UsuarioDesativadoDto;
import br.com.cruz.vita.usuario.dto.UsuariosAtivosDTO;
import br.com.cruz.vita.usuario.service.UsuarioService;
//import lombok.extern.slf4j.Slf4j;

/*
 * 
 * @autor : Desenvolvedor Cleber 
 * @nome : Construindo uma APi de cadastro de usuarios
 * @version : 1.0
 * @date : 03/02/2023 ás 17:28
 * 
 * 
 */
@RestController 
@RequestMapping("/usuario")
//@Slf4j
public class UsuarioController {
	
	@Autowired
	private UsuarioService service ;
	
//	@Value("${ambiente.deploy}")
//	private String profile;
//	
//	public void log() {
//		log.info("Ambiente :"  + profile);
//	}
		
	@PostMapping(path = "/cadastro")
	public ResponseEntity<String> criandoNovoUsuario (@RequestBody UsuarioDTO usuario){		
		return ResponseEntity.status(200).body(service.criaUsuarioNovo(usuario));
	}
			
	@PostMapping("/criar/lote")
	public  ResponseEntity<String> listaDeUsuario (@RequestBody List<UsuarioDTO> usuario){
		return ResponseEntity.status(200).body(service.CriarLoteUsuario(usuario).getBody());
	}
		
	@PutMapping("/editar/{email}")
	public ResponseEntity<String> editaUsuario (@RequestBody UsuarioDTO usuario, @PathVariable String email ) {
		String mensagem = service.editarUsuario(usuario, email);
		return ResponseEntity.status(200).body(mensagem);
	}
	
	@GetMapping("/listar/desativados")
	public ResponseEntity<List<UsuarioDesativadoDto>> listaDeDesativados(){
		return ResponseEntity.status(200).body(service.buscarPorDesativados());
	}
	
	@GetMapping("/listar/ativados")
	public ResponseEntity<List<UsuariosAtivosDTO>> listaDeAtivados(){
		return ResponseEntity.status(200).body(service.buscarPorAtivados());
	}
	
	@GetMapping(path = "lista")
	public  ResponseEntity<List<UsuarioDTO>> listaBanco(){
		return ResponseEntity.status(200).body(service.ListaUsuario());
	}
	
	@GetMapping ("/buscar/{email}")
	public ResponseEntity<String> buscarUsuario (@PathVariable String email){
		return ResponseEntity.status(200).body(service.buscaPorEmail(email));
	}
			 
	@DeleteMapping ("/deletar/{email}")
	public ResponseEntity<String> excluirPorEmail (@PathVariable String email){
		return ResponseEntity.status(200).body(service.ExcluirPorEmail(email));
	}	
		
}
