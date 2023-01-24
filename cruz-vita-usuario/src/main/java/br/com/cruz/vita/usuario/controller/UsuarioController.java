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
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	private UsuarioService usuarioService;

	@GetMapping("/lista")
	public ResponseEntity<List<UsuarioModel>> listaUsuarios() {
		List<UsuarioModel> lista = usuarioRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping("/buscar/{email}")
	public ResponseEntity<String> buscarPeloEmail(@PathVariable String email){
		UsuarioModel buscarEmail = usuarioRepository.findByEmail(email);
		String cpfUsuario = buscarEmail.getCpf();
		return ResponseEntity.status(200).body("Email possui cadastro vinculado com o cpf: " + cpfUsuario);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuario) {
		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);
		return ResponseEntity.status(201).body("usuário criado com sucesso!");
	}

	@PutMapping("/atualizar/{email}")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDTO usuario, @PathVariable String email) {
        UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuario, usuarioModel);
        usuarioModel.setId(buscaEmail.getId());
        String cpfUsuario = usuarioModel.getCpf();
        usuarioRepository.save(usuarioModel);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário vinculado ao cpf " + cpfUsuario + " atualizado com sucesso.");
    }

	@DeleteMapping("/deletar/{email}")
    public ResponseEntity<String> deletarEmail(@PathVariable String email){
        UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
        String cpfUsuario = buscaEmail.getCpf();
        usuarioRepository.delete(buscaEmail);
        return ResponseEntity.status(200).body("usuário vinculado ao cpf " + cpfUsuario + " deletado com sucesso");
    }
	
	
	@PostMapping("/cadastrar/lote")
    public ResponseEntity<String> criarUsuarioLote(@RequestBody List<UsuarioDTO> usuario) {
        for (UsuarioDTO itemLista : usuario) {
            var usuarioModel = new UsuarioModel();
            BeanUtils.copyProperties(itemLista, usuarioModel);
            usuarioRepository.save(usuarioModel);
        }
        return ResponseEntity.status(201).body("Lote cadastrado com sucesso");
    }
	
	
	

}
