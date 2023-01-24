package cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.UsuarioModel;
import repository.UsuarioJpa;

@RestController
@RequestMapping("/usuarios")
public class CadastroController {
	
	@Autowired
	public UsuarioJpa usuarioJpa ;
	
//	@GetMapping ("/usuario")
//	private List<UsuarioJpa> listaDeUsuarios () {
//		return List<UsuarioJpa> usuarioJpa.findAll() ;
//	}
	
	
	@GetMapping  ("/usuarios")
	public  String  Usuario (@RequestBody UsuarioModel dados ) {
		return "null"; 
	}
	
	
}
