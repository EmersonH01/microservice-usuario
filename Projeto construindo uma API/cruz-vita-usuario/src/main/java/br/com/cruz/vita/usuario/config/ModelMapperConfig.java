package br.com.cruz.vita.usuario.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	protected ModelMapper modelMapper;
	
	public ModelMapperConfig() {
		 this.modelMapper = new ModelMapper();
	}

}
