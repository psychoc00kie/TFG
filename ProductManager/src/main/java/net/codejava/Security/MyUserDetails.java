package net.codejava.Security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUserDetails implements UserDetails {

	private User user;
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public MyUserDetails(User user) {
		this.user = user;
		this.user.password = encoder.encode(user.getPassword());
	}

	public Long getId()
	{
		return this.user.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		String role = "";	// 0 Admin company user, 1 company regular user , 2 client , 3 bot, 4 box
		switch (user.getType()){
			case 0:
				role = "ROLE_ADMIN";
				break;
			case 1:
				role ="ROLE_USER";
				break;
			case 2:
				role = "ROLE_CLIENT";
				break;
			case 3:
				role="ROLE_BOT";
				break;
			case 4:
				role = "ROLE_BOX";
				break;
			default:
				role = "ROLE_USER";
				break;
		}

		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
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

}
