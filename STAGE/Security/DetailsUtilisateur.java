package STAGE.Security; 

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import STAGE.Entity.Utilisateur;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DetailsUtilisateur implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().toString());
		return Collections.singletonList(grantedAuthority);
	}

	@Override
	public String getPassword() {
		return utilisateur.getMdp();
		}
	
	public Long getId() {
		return utilisateur.getId();
	}

	@Override
	public String getUsername() {
		return utilisateur.getMail();
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
		return utilisateur.isActif();
	}

}
