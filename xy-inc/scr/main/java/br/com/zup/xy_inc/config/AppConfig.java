package br.com.zup.xy_inc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Classe que configura a aplicação.
 * @author luizhenriquesantana
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.zup.xy_inc")
public class AppConfig {
	
}
