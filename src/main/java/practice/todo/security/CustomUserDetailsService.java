package practice.todo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import practice.todo.user.AppUser;
import practice.todo.user.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AppUser user = (AppUser) userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("User Name is Not Avialable"));
		
		return new CustomUserDetails(user);
	}
}
