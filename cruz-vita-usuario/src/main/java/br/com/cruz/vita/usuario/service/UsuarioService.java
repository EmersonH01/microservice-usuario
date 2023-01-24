package br.com.cruz.vita.usuario.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruz.vita.usuario.model.UsuarioModel;
import br.com.cruz.vita.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public List<UsuarioModel> listaUsuario(){
		List<UsuarioModel> lista = usuarioRepository.findAll();
		return lista;
	}
	
	
	
	
	
	

}
