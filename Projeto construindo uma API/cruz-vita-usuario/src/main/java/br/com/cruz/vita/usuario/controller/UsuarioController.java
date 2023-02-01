package br.com.cruz.vita.usuario.controller;

import java.util.List;

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
import br.com.cruz.vita.usuario.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service ;
	
//	@GetMapping("/listar/desativados"){
//		public ResponseEntity<List<UsuarioModel>> ListaDesativados (){
//			return ResponseEntity.status(200).body(service.);
//		}
//	}
	
	
	@GetMapping(path = "lista")
	public  ResponseEntity<List<UsuarioModel>> listaBanco (){
		return ResponseEntity.status(200).body(service.ListaUsuario());
	}
	
	@GetMapping ("/buscar/{email}")
	public ResponseEntity<String> buscarUsuario (@PathVariable String email){
		return ResponseEntity.status(200).body(service.BuscaPorEmail(email));
	}
	
	@GetMapping("/criarLote")
	public  ResponseEntity<String> listaDeUsuario (@RequestBody List<UsuarioDTO> usuario){
		service.CriarLoteUsuario(usuario);
		return ResponseEntity.status(200).body("Lote de usuarios criado ");
	}
   	
	@PostMapping(path = "/cadastro")
	public ResponseEntity<String> criandoNovoUsuario (@RequestBody UsuarioDTO usuario){
		service.criaUsuarioNovo(usuario);
		return ResponseEntity.status(201).body("Um novo usuario foi cadastrado");
	}
		  
	@PutMapping("/editar/{email}")
	public ResponseEntity<String> editaUsuario ( @RequestBody UsuarioDTO usuario, @PathVariable("email") String email ) {
		UsuarioDTO model = service.editaUsuario(usuario, email);
		return ResponseEntity.status(200).body("Usuario vinculado ao cpf "  + model.getCpf() +  " atualizado com sucesso  "  + model.toString());
	}
			
	@DeleteMapping ("/deletar/{email}")
	public ResponseEntity<String> excluirPorEmail (@PathVariable String email){
		return ResponseEntity.status(200).body(service.ExcluirPorEmail(email));
	}
	
}
