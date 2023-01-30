package br.com.cruz.vita.usuario.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

		return "Email possui cadastro vinculado com o cpf: " + usuarioRepository.findByEmail(email).getCpf();
	}

	/* busca usuarios desativados pela data de exclusao */
	public List<UsuarioModel> buscarDesativados() {
//		LocalDateTime usuarioExcluido = usuarioModel.getDataExclusao();

		List<UsuarioModel> lista = usuarioRepository.findByDataExclusao();

		return lista;
	}

	/* busca usuarios desativados pela data de ativos */
	public List<UsuarioModel> buscarAtivados() {
//		LocalDateTime usuarioExcluido = usuarioModel.getDataExclusao();

		List<UsuarioModel> lista = usuarioRepository.findByDataInclusao();

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
			if(verificarSeExiste(itemLista)) {
				
				throw new DataIntegrityViolationException("O CPF: " + itemLista.getCpf() + " já existe em nosso sistema");
			}
			UsuarioModel usuarioModel = modelMapper.map(itemLista, UsuarioModel.class);

			usuarioRepository.save(usuarioModel);

		}
		return "Lote cadastrado com sucesso";

	}

	/* verifica se o CPF já é existente no banco de dados */
	public Boolean verificarSeExiste(UsuarioDTO usuarioDTO) {

		if (usuarioRepository.findByCpf(usuarioDTO.getCpf()).isEmpty()) {
			return false;
		}

		return true;
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

	/* exclusão logica de usuario */
	public String deletarPorEmail(String email) {
		UsuarioModel buscaEmail = usuarioRepository.findByEmail(email);
//		DateTimeFormatter formatarDataEHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		buscaEmail.setDataExclusao(LocalDateTime.now());
		String cpfUsuario = buscaEmail.getCpf();
		usuarioRepository.save(buscaEmail);

		return "usuário vinculado ao cpf " + cpfUsuario + " deletado com sucesso!";
	}

}
