package br.com.alura.forum_hub.domain.usuario;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.alura.forum_hub.domain.usuario.dto.DadosAtualizacaoUsuario;
import br.com.alura.forum_hub.domain.usuario.dto.DadosCadastroUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_perfil_usuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private Set<Perfil> perfis;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis.stream()
				.map(p -> new SimpleGrantedAuthority(p.getNome()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
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

	public Usuario(DadosCadastroUsuario dados, String senhaCriptografada, Set<Perfil> perfis) {
		this.email = dados.email();
		this.nome = dados.nome();
		this.senha = senhaCriptografada;
		this.perfis = perfis;
	}

	public void atualizar(DadosAtualizacaoUsuario dados, String senhaCriptografada) {
		if (dados.email() != null) {
			this.email = dados.email();
		}
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (senhaCriptografada != null) {
			this.senha = senhaCriptografada;
		}
	}
}
