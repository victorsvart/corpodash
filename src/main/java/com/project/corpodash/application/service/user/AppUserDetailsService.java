package com.project.corpodash.application.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.project.corpodash.domain.base.valueobject.Email;
import com.project.corpodash.domain.user.interfaces.UserRepository;
import com.project.corpodash.infrastructure.security.AppUserDetails;

@Component
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public AppUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    System.out.println("Loading user by email: " + email);
    AppUserDetails user = userRepository.findByEmail(Email.of(email))
        .map(AppUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return user;
  }
}
