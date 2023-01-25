package br.com.cruz.vita.usuario.service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cruz.vita.usuario.dto.UsuarioDTO;
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

	
	/* listar todos os usuarios através do metodo findAll da JpaRepository */	 
	public List<UsuarioModel> listarUsuario() {
		List<UsuarioModel> lista = usuarioRepository.findAll();
		return lista;
	}

	/* Busca todos usuarios por email que é passado na Url */
	public String buscarPorEmail(String email) {
		UsuarioModel buscarEmail = usuarioRepository.findByEmail(email);
		String cpfUsuario = buscarEmail.getCpf();

		return "Email possui cadastro vinculado com o cpf: " + cpfUsuario;
	}

	/* busca usuarios desativados pela data de exclusao */
	public List<UsuarioModel> buscarDesativado() {

		List<UsuarioModel> lista = usuarioRepository.findAll();
		for (UsuarioModel usuarioModel : lista) {
			if (!usuarioModel.getDataExclusao().toString().isEmpty()) {
				lista.add(usuarioModel);

				lista.add(usuarioModel);
				return lista;

			} else {
				return null;
			}
		}
		return lista;
	}

	/* cadastra um novo usuario */
	public String cadastrarUsuario(UsuarioDTO usuario) {
		UsuarioModel usuarioNovo = modelMapper.map(usuario, UsuarioModel.class);
		usuarioRepository.save(usuarioNovo);

		return "usuário criado com sucesso!";
	}

	/* cadastra uma lista de usuarios */
	public String cadastrarPorLote(List<UsuarioDTO> usuario) {

		for (UsuarioDTO itemLista : usuario) {
			UsuarioModel usuarioModel = modelMapper.map(itemLista, UsuarioModel.class);

			usuarioRepository.save(usuarioModel);
		}

		return "Lote cadastrado com sucesso";
	}

	/* atualiza o usuario através do email passado e retorna uma mensagem */
	public String atualizarViaEmail(UsuarioDTO usuario, String email) {
		UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
		UsuarioModel usuarioModel = modelMapper.map(usuario, UsuarioModel.class);
		usuarioModel.setId(buscaEmail.getId());
		String cpfUsuario = usuarioModel.getCpf();
		usuarioRepository.save(usuarioModel);

		return "Usuário vinculado ao cpf " + cpfUsuario + " atualizado com sucesso.";
	}

	/* exclui de uma vez por todas o usuario do banco de dados */
	public String excluirPorEmail(String email) {
		UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
		String cpfUsuario = buscaEmail.getCpf();
		usuarioRepository.delete(buscaEmail);
		return "usuário vinculado ao cpf " + cpfUsuario + " excluido com sucesso";
	}

	/* exclusão logica do usuario */
	public String deletarPorEmail(String email) {
		UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
//		DateTimeFormatter formatarDataEHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		buscaEmail.setDataExclusao(LocalDateTime.now());
		String cpfUsuario = buscaEmail.getCpf();
		usuarioRepository.save(buscaEmail);
		return "usuário vinculado ao cpf " + cpfUsuario + " deletado com sucesso!";
	}

}
