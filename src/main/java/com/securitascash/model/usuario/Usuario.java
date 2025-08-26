package com.securitascash.model.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.securitascash.model.conta.Conta;
import com.securitascash.model.usuario.inherit.Administrador;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "Senha é obrigatório.")
    private String senha;

    @NotBlank(message = "Email é obrigatório.")
    private String email;

    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Conta> contas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Define as "regras" ou "papéis" do usuário.
        // Ex: "ROLE_ADMIN" para Administrador, "ROLE_USER" para Correntista
        String role = this instanceof Administrador ? "ROLE_ADMIN" : "ROLE_USER";
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.senha; // Retorna a senha
    }

    @Override
    public String getUsername() {
        return this.email; // Usaremos o email como username
    }

    // Métodos para controle de conta. Por enquanto, podemos retornar true.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Você pode conectar isso ao seu campo `isBlocked` em Correntista no futuro
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
