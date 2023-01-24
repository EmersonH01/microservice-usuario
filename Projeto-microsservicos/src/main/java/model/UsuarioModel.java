package model;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class UsuarioModel {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	 
	@Column 
	private String email ;
	private String senha ;
	private String cpf ;
	
}
