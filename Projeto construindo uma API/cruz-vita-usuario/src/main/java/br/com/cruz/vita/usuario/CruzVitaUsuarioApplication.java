package br.com.cruz.vita.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("br.com.cruz.vita.usuario.repository")
@EnableAutoConfiguration(exclude= {MongoAutoConfiguration.class})
@EntityScan("br.com.cruz.vita.usuario.model")
public class CruzVitaUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruzVitaUsuarioApplication.class, args);
	}

}
