package com.securitascash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Permite acesso a recursos estáticos
                .requestMatchers("/usuario/login", "/usuario/register").permitAll() // Permite acesso à página de login e registro
                .requestMatchers("/correntistas/**", "/categorias/**").hasRole("ADMIN") // só ADMIN acessa
                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
                )
                .formLogin(form -> form
                .loginPage("/usuario/login") // Sua página de login customizada
                .loginProcessingUrl("/usuario/login") // URL que o form deve submeter (Spring Security cuida disso)
                .defaultSuccessUrl("/contas", true) // Página para redirecionar após sucesso
                .failureUrl("/usuario/login?error=true") // Página para redirecionar após falha
                .usernameParameter("email") // Informa qual campo do form é o username
                .passwordParameter("password") // Informa qual campo do form é a senha
                )
                .logout(logout -> logout
                .logoutUrl("/logout") // URL para acionar o logout
                .logoutSuccessUrl("/") // Página para redirecionar após logout
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/usuario/403") // define a página customizada
                );

        return http.build();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
